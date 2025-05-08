package tpi.backend.e_commerce.categoryTests.integrationTests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.repositories.ICategoryRepository;
import tpi.backend.e_commerce.services.category.DeleteCategoryService;
import tpi.backend.e_commerce.validation.Validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PruebaN07 {

    @Mock
    private ICategoryRepository categoryRepository;

    @Mock
    private Validation validation;

    @InjectMocks
    private DeleteCategoryService deleteCategoryService;

    @Test
    void recoverShouldReturn404WhenCategoryDoesNotExist() {
        Long categoryId = 1L;
        ResponseEntity<?> responseEntity = ResponseEntity.status(404).body("No existe una categoria con ese id");

        //Simulamos el repositorio para que responda como "vacío", es decir, no hay categoría con ese id
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());
        when(validation.validate("id", "No existe una categoria con ese id", 404))
                .thenReturn((ResponseEntity<Map<String, String>>) responseEntity);

        ResponseEntity<?> response = deleteCategoryService.recover(categoryId);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("No existe una categoria con ese id", response.getBody());
    }

    @Test
    void recoverShouldReturn200WhenCategoryIsRecoveredSuccessfully() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setDeleted(true);
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        ResponseEntity<?> response = deleteCategoryService.recover(categoryId);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}

