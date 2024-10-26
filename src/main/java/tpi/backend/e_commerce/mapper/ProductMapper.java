package tpi.backend.e_commerce.mapper;


import java.util.List;

import tpi.backend.e_commerce.dto.ProductDTO.CreateProductDTO;
import tpi.backend.e_commerce.dto.ProductDTO.ResponseProductDTO;
import tpi.backend.e_commerce.models.SubCategory;
import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.models.Product;

public class ProductMapper {

    //El mapper recibira el DTO y la categoria. Si no recibiera la categoria, deberia hacer una 
    //consulta a la BD para traerla, lo cual no es responsabilidad de esta clase
    public static Product toEntity(
        CreateProductDTO createProductDTO, SubCategory subCategory, Brand brand
        ){ 
        return new Product(
            createProductDTO.getName(),
            createProductDTO.getDescription(),
            createProductDTO.getPrice(),
            createProductDTO.getStock(),
            createProductDTO.getStockMin(),
            createProductDTO.getImageURL(),
            createProductDTO.getColor(),
            createProductDTO.getSize(),
            brand,
            subCategory
        );
        
    }

//El DTO de producto mostrara el nombre de la categoria, subcategoria y de la marca
    public static ResponseProductDTO toDTO(Product product){
        return new ResponseProductDTO(
            product.getId(), 
            product.getName(), 
            product.getDescription(),
            product.getPrice(), 
            product.getStock(),
            product.getStockMin(),
            product.getImageURL(),
            product.getColor(),
            product.getSize(),
            product.getSubCategory().getCategory().getName(),
            product.getSubCategory().getName(), 
            product.getBrand().getName(),
            product.getCreationDatetime(),
            product.getUpdateDatetime(),
            product.getDeleteDatetime()
        );
    }   

    public static Product toUpdate(CreateProductDTO productDTO,Long id ,SubCategory SubCategory, Brand brand){ 
        return new Product(
            id, 
            productDTO.getName(), 
            productDTO.getDescription(), 
            productDTO.getPrice(),
            productDTO.getStock(),
            productDTO.getStockMin(),
            productDTO.getImageURL(),
            productDTO.getColor(),
            productDTO.getSize(),
            brand,
            SubCategory
        );
    }

    public static List<ResponseProductDTO> toDTOList(List<Product> products){ //Convierte una lista de productos a una lista de ProductDto
        return products.stream() //Convierte la lista a un stream, para poder utilizar el metodo map
        .map(p -> ProductMapper.toDTO(p)) //Convierte los productos en DTO
        .toList(); //Convierte el stream resultante del map en una lista
    }
}
