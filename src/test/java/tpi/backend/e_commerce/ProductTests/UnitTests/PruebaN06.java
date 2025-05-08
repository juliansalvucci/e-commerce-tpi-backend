package tpi.backend.e_commerce.ProductTests.UnitTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.repositories.ISubCategoryRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import tpi.backend.e_commerce.repositories.ICategoryRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class PruebaN06 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IBrandRepository brandRepository;

    @Autowired
    private ISubCategoryRepository subCategoryRepository;

    @Autowired
    private ICategoryRepository categoryRepository;


    @BeforeEach
    void setUp() {
        productRepository.deleteAll();
        brandRepository.deleteAll();
        subCategoryRepository.deleteAll();
    }

    @Test
    void shouldReturnOnlyActiveProducts() throws Exception {
        // Crear Brand y SubCategory necesarios
        Brand brand = new Brand();
        brand.setName("POCO");
        brand = brandRepository.save(brand);

        Category category = new Category("Ropa");
        categoryRepository.save(category);

        SubCategory subCategory = new SubCategory();
        subCategory.setName("Zapatillas");
        subCategory.setCategory(category);
        subCategory = subCategoryRepository.save(subCategory);

        // Producto ACTIVO
        Product activeProduct = new Product();
        activeProduct.setName("Producto Activo");
        activeProduct.setDescription("Desc");
        activeProduct.setPrice(100.0);
        activeProduct.setStock(10L);
        activeProduct.setStockMin(2L);
        activeProduct.setImageURL("http://img.com/activo.jpg");
        activeProduct.setColor("Rojo");
        activeProduct.setSize("M");
        activeProduct.setBrand(brand);
        activeProduct.setSubCategory(subCategory);
        activeProduct.setDeleted(false);
        productRepository.save(activeProduct);

        // Producto ELIMINADO
        Product deletedProduct = new Product();
        deletedProduct.setName("Producto Eliminado");
        deletedProduct.setDescription("Desc");
        deletedProduct.setPrice(200.0);
        deletedProduct.setStock(5L);
        deletedProduct.setStockMin(1L);
        deletedProduct.setImageURL("http://img.com/borrado.jpg");
        deletedProduct.setColor("Azul");
        deletedProduct.setSize("L");
        deletedProduct.setBrand(brand);
        deletedProduct.setSubCategory(subCategory);
        deletedProduct.setDeleted(true);
        productRepository.save(deletedProduct);

        // Ejecutar la request y verificar solo el producto activo
        mockMvc.perform(get("/product")  // reemplazá "/products" si tu endpoint es distinto
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Producto Activo"));
    }

    @Test
    void shouldReturnOnlyInactiveProducts() throws Exception {
        // Crear Brand y SubCategory necesarios
        Brand brand = new Brand();
        brand.setName("POCO");
        brand = brandRepository.save(brand);

        Category category = new Category("Ropa");
        categoryRepository.save(category);

        SubCategory subCategory = new SubCategory();
        subCategory.setName("Zapatillas");
        subCategory.setCategory(category);
        subCategory = subCategoryRepository.save(subCategory);

        // Producto ACTIVO
        Product activeProduct = new Product();
        activeProduct.setName("Producto Activo");
        activeProduct.setDescription("Desc");
        activeProduct.setPrice(100.0);
        activeProduct.setStock(10L);
        activeProduct.setStockMin(2L);
        activeProduct.setImageURL("http://img.com/activo.jpg");
        activeProduct.setColor("Rojo");
        activeProduct.setSize("M");
        activeProduct.setBrand(brand);
        activeProduct.setSubCategory(subCategory);
        activeProduct.setDeleted(false);
        productRepository.save(activeProduct);

        // Producto ELIMINADO
        Product deletedProduct = new Product();
        deletedProduct.setName("Producto Eliminado");
        deletedProduct.setDescription("Desc");
        deletedProduct.setPrice(200.0);
        deletedProduct.setStock(5L);
        deletedProduct.setStockMin(1L);
        deletedProduct.setImageURL("http://img.com/borrado.jpg");
        deletedProduct.setColor("Azul");
        deletedProduct.setSize("L");
        deletedProduct.setBrand(brand);
        deletedProduct.setSubCategory(subCategory);
        deletedProduct.setDeleted(true);
        productRepository.save(deletedProduct);

        // Ejecutar la request y verificar solo el producto activo
        mockMvc.perform(get("/product/deleted")  // reemplazá "/products" si tu endpoint es distinto
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Producto Eliminado"));
    }

}
