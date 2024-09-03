package tpi.backend.e_commerce.mapper;


import java.util.List;
import tpi.backend.e_commerce.dto.CreateProductDTO;
import tpi.backend.e_commerce.dto.ResponseProductDTO;

import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.models.Product;

public class ProductMapper {

    public static Product toEntity(CreateProductDTO createProductDTO, Category category){ 
        //El mapper recibira el DTO y la categoria. Si no recibiera la categoria, deberia hacer una 
        //consulta a la BD para traerla, lo cual no es responsabilidad de esta clase
        return new Product(
            createProductDTO.getName(), createProductDTO.getDescription(), 
            createProductDTO.getPrice(), category
        );
    }

    public static ResponseProductDTO toDTO(Product product){
        return new ResponseProductDTO(
            product.getId(), product.getName(), product.getDescription(), 
            product.getPrice(), product.getCategory().getName()
            //El DTO de producto mostrara solo el nombre de la categoria
        );
    }   

    public static Product toUpdate(CreateProductDTO updateProductDTO,Long id ,Category category){ //Este metodo sera usado para la actualizacion de productos
        return new Product(
            id, updateProductDTO.getName(), 
            updateProductDTO.getDescription(), updateProductDTO.getPrice(),category
        );
    }

    public static List<ResponseProductDTO> toDTOList(List<Product> products){ //Convierte una lista de productos a una lista de ProductDto
        return products.stream() //Convierte la lista a un stream, para poder utilizar el metodo map
        .map(p -> ProductMapper.toDTO(p)) //Convierte los productos en DTO
        .toList(); //Convierte el stream resultante del map en una lista
    }
}
