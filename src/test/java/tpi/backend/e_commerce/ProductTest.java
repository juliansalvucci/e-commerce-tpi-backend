package tpi.backend.e_commerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        @Transactional
        void testCreateProductWithShortName() throws Exception {
                // Crear un producto con un nombre demasiado corto (1 carácter)
                CreateProductDTO productDtoShortName = new CreateProductDTO(
                                "A", // Nombre demasiado corto
                                "Descripción válida",
                                100.0,
                                50L,
                                10L,
                                "http://example.com/image.jpg",
                                "Rojo",
                                "XL",
                                1L,
                                1L);

                mockMvc.perform(post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDtoShortName)))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.name").value("Debe tener entre 2 y 60 caracteres"));
        }

        @Test
        @Transactional
        void testCreateProductWithLongName() throws Exception {
                // Crear un producto con un nombre demasiado largo (61 caracteres)
                CreateProductDTO productDtoLongName = new CreateProductDTO(
                                "A".repeat(61), // Nombre demasiado largo
                                "Descripción válida",
                                100.0,
                                50L,
                                10L,
                                "http://example.com/image.jpg",
                                "Rojo",
                                "XL",
                                1L,
                                1L);

                mockMvc.perform(post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDtoLongName)))
                                .andExpect(status().isBadRequest())
                                .andExpect(jsonPath("$.name").value("Debe tener entre 2 y 60 caracteres"));
        }

        @Test
        @Transactional
        void testCreateProductWithValidName() throws Exception {
                // Crear un producto con un nombre dentro del rango válido
                CreateProductDTO productDtoValidName = new CreateProductDTO(
                                "Nombre válido",
                                "Descripción válida",
                                100.0,
                                50L,
                                10L,
                                "http://example.com/image.jpg",
                                "Rojo",
                                "XL",
                                1L,
                                1L);

                mockMvc.perform(post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDtoValidName)))
                                .andExpect(status().isCreated()); // Asegura que el producto se cree correctamente
        }

        @Test
        @Transactional
        void testCreateProductWithValidData() throws Exception {
                CreateProductDTO productDto = new CreateProductDTO(
                                "Producto de prueba válido",
                                "descripción",
                                1.0,
                                50L,
                                10L,
                                "http://example.com/image.jpg",
                                "Rojo",
                                "XL",
                                1L,
                                1L);

                // Realizar la solicitud POST simulada
                mockMvc.perform(post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDto)))
                                .andExpect(status().isCreated()); // Asegura que el estatus sea 200 OK
                // .andExpect(jsonPath("$.message").value("Producto creado con éxito"));
        }

        @Test
        @Transactional
        void testCreateProductNegativePrice() throws Exception {
                CreateProductDTO productDto = new CreateProductDTO(
                                "Producto de prueba válido",
                                "descripción",
                                -2.0,
                                50L,
                                10L,
                                "http://example.com/image.jpg",
                                "Rojo",
                                "XL",
                                1L,
                                1L);

                // Realizar la solicitud POST simulada
                mockMvc.perform(post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDto)))
                                .andExpect(status().isBadRequest()); // Asegura que el estatus sea 200 OK
                // .andExpect(jsonPath("$.message").value("Producto creado con éxito"));
        }

        @Test
        @Transactional
        void testCreateProductWthoutImageURL() throws Exception {
                CreateProductDTO productDto = new CreateProductDTO(
                                "Producto de prueba válido",
                                "descripción",
                                100.0,
                                50L,
                                10L,
                                "",
                                "Rojo",
                                "XL",
                                1L,
                                1L);

                // Realizar la solicitud POST simulada
                mockMvc.perform(post("/product")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(productDto)))
                                .andExpect(status().isCreated()); // Asegura que el estatus sea 200 OK
                // .andExpect(jsonPath("$.message").value("Producto creado con éxito"));
        }

        @Test
        @Transactional
        void testDeleteProductSuccess() throws Exception {
                Long productId = 20L;

                // Realizar la solicitud DELETE simulada
                mockMvc.perform(delete("/product/{id}", productId)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isConflict()) // Asegura que el estatus sea 204 No Content
                                .andExpect(jsonPath("$.stock")
                                                .value("No se puede eliminar un producto cuyo stock no es 0"));

        }

}
