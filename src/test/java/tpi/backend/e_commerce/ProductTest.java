package tpi.backend.e_commerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void testCreateProductWithNonExistentSubCategoryId() throws Exception {
                // Crear un objeto CreateProductDTO con un subCategoryId que no existe
                CreateProductDTO productDto = new CreateProductDTO(
                                "Producto de prueba",
                                "descripción",
                                100.0,
                                50L,
                                10L,
                                "http://example.com/image.jpg",
                                1L,
                                9999L // subCategoryId inexistente
                );

                mockMvc.perform(post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDto)))
                                .andExpect(status().isNotFound()) // Asegura que el estatus sea 404 Not Found
                                .andExpect(jsonPath("$.subCategoryId").value("La sub categoria ingresada no existe"));
        }
        /*
         * @Test
         * void testCreateProductWithValidData() throws Exception {
         * CreateProductDTO productDto = new CreateProductDTO(
         * "Producto de prueba",
         * "descripción",
         * 100.0,
         * 50L,
         * 10L,
         * "http://example.com/image.jpg",
         * 1L,
         * 2L);
         * 
         * // Realizar la solicitud POST simulada
         * mockMvc.perform(post("/product")
         * .contentType(MediaType.APPLICATION_JSON)
         * .content(objectMapper.writeValueAsString(productDto)))
         * .andExpect(status().isOk()) // Asegura que el estatus sea 200 OK
         * .andExpect(jsonPath("$.message").value("Producto creado con éxito"));
         * }
         * 
         * @Test
         * void testCreateProductWithInvalidData() throws Exception {
         * // Usar el constructor para crear un objeto CreateProductDTO con datos
         * inválidos
         * CreateProductDTO productDto = new CreateProductDTO(
         * "", // name vacío
         * "Esta descripción es demasiado larga para ser aceptada por el DTO. Este texto excede los 100 caracteres permitidos por la anotación @Size."
         * , // description
         * // inválida
         * -5.0, // price negativo
         * -1L, // stock negativo
         * -1L, // stockMin negativo
         * "http://example.com/image.jpg", // imageURL
         * 1L, // brandId
         * 2L // subCategoryId
         * );
         * 
         * // Realizar la solicitud POST simulada
         * mockMvc.perform(post("/product")
         * .contentType(MediaType.APPLICATION_JSON)
         * .content(objectMapper.writeValueAsString(productDto)))
         * .andExpect(status().isBadRequest()) // Asegura que el estatus sea 400 Bad
         * Request
         * .andExpect(jsonPath("$.errors.name").value("No puede estar vacio"))
         * .andExpect(jsonPath("$.errors.price").value("Debe ser mayor o igual a 0"))
         * .andExpect(jsonPath("$.errors.stock").value("Debe ser mayor o igual a 0"))
         * .andExpect(jsonPath("$.errors.stockMin").value("Debe ser mayor o igual a 0"))
         * ;
         * }
         */
}
