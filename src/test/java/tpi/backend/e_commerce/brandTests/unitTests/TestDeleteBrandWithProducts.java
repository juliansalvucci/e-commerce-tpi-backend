package tpi.backend.e_commerce.brandTests.unitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.repositories.IBrandRepository;
import tpi.backend.e_commerce.services.brand.DeleteBrandService;
import tpi.backend.e_commerce.validation.Validation;


/*
Este test corresponde al caso de prueba 1
Testeara el comportamiento del servicio al eliminar una marca con y sin productos
*/
@ExtendWith(MockitoExtension.class)
public class TestDeleteBrandWithProducts {
    
    @Mock
    private IBrandRepository brandRepository;

    @Mock
    private Validation validation;
    
    @InjectMocks
    private DeleteBrandService deleteBrandService;

    Brand brand;

    @BeforeEach
    void setUp() {
        brand = new Brand();

        when(brandRepository.findById(anyLong())).thenReturn((Optional.of(brand)));
        //Aqui simulo que el id pasado siempre es de una marca existente en la BD

        when(brandRepository.hasBrandProducts(anyLong()))
            .thenAnswer(invocation -> invocation.getArgument(0).equals(1L));
        //Aqui estoy simulando que la marca con id 1 tiene productos y el resto no

    }
    //Long.valueOf(1): equals espera un objeto de tipo Long, si colocara 1L daria error porque podria ser un long

    @Test
    void deleteBrandWithProducts(){
        
        when(validation.validate(eq("id"), eq("La marca tiene productos asociados"), eq(409)))
        .thenReturn(ResponseEntity.status(409).body(Map.of("id", "La marca tiene productos asociados")));

        brand.setId(1L);
        ResponseEntity<?> serviceResponse = deleteBrandService.delete(1L);
        assertEquals(HttpStatus.CONFLICT, serviceResponse.getStatusCode());
        assertEquals(Map.of("id", "La marca tiene productos asociados"), serviceResponse.getBody());
    }
    //En este metodo verifico que al llamar al metodo brandService

    @Test
    void deleteBrandWithoutProducts(){
        brand.setId(2L);
        ResponseEntity<?> serviceResponse = deleteBrandService.delete(2L);
        assertEquals(HttpStatus.OK, serviceResponse.getStatusCode());
        assertEquals("La marca fue eliminada con exito!", serviceResponse.getBody());
    }

}
