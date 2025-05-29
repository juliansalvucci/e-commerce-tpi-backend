package tpi.backend.e_commerce.subcategoryTests.UnitTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.repositories.ISubCategoryRepository;
import tpi.backend.e_commerce.services.subCategory.DeleteSubCategoryService;
import tpi.backend.e_commerce.validation.Validation;

@SpringBootTest
class DeleteSubcategoryTest {

    @Mock
    private ISubCategoryRepository subCategoryRepository;

    @Mock
    private Validation validation;

    @InjectMocks
    private DeleteSubCategoryService deleteSubCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDelete_SubCategoryHasProducts() {
        Long id = 1L;
        SubCategory subCategory = new SubCategory();
        when(subCategoryRepository.findById(id)).thenReturn(Optional.of(subCategory));
        when(subCategoryRepository.hasSubCategoryProducts(id)).thenReturn(true);
        when(validation.validate("id", "La subcategoria tiene productos asociados", 409))
                .thenReturn(ResponseEntity.status(409).body(Map.of( "id", "La subcategoria tiene productos asociados")));


        ResponseEntity<?> response = deleteSubCategoryService.delete(id);

        assertEquals(409, response.getStatusCode().value());
        assertEquals("La subcategoria tiene productos asociados", ((Map<?, ?>) Objects.requireNonNull(response.getBody())).get("id"));

        verify(subCategoryRepository, times(1)).findById(id);
        verify(subCategoryRepository, times(1)).hasSubCategoryProducts(id);
        verify(validation, times(1)).validate("id", "La subcategoria tiene productos asociados", 409);
    }

    @Test
    void testDelete_SuccessfulDeletion() {
        Long id = 1L;
        SubCategory subCategory = new SubCategory();
        when(subCategoryRepository.findById(id)).thenReturn(Optional.of(subCategory));
        when(subCategoryRepository.hasSubCategoryProducts(id)).thenReturn(false);

        ResponseEntity<?> response = deleteSubCategoryService.delete(id);

        assertEquals(204, response.getStatusCode().value());
        verify(subCategoryRepository, times(1)).findById(id);
        verify(subCategoryRepository, times(1)).hasSubCategoryProducts(id);
        verify(subCategoryRepository, times(1)).save(subCategory);
        assertTrue(subCategory.isDeleted());
    }
}


