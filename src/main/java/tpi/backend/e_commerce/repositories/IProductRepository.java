package tpi.backend.e_commerce.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import tpi.backend.e_commerce.models.Product;

public interface IProductRepository extends CrudRepository<Product,Long>{
    
    @Query("select p from Product p where p.deleted = false")
    List<Product> findAllActive(); 
    //Busca todos los productos que no han sido eliminados

    @Query("select p from Product p where p.deleted = true")
    List<Product> findAllDeleted();
    
    @Query("select p from Product p where p.id = ?1 and p.deleted = false")
    Optional<Product> findActiveById(Long id);
    //Solo devolvera el producto si este no esta eliminado
    
    @Query("select p from Product p where p.id = ?1 and p.deleted = true")
    Optional<Product> findDeletedById(Long id);
    //Solo traera el producto si este esta eliminado    

    @Query("select CASE when COUNT(p)>0 then true else false end from Product p where UPPER(p.name) = UPPER(?1) AND UPPER(p.color) = UPPER(?2)")
    boolean existsByNameAndColor(String name ,String color);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
       "FROM Product p " +
       "WHERE UPPER(p.name) = UPPER(?1) " +
       "AND UPPER(p.color) = UPPER(?2) " +
       "AND p.id <> ?3")
    boolean existsByNameAndColorExceptId(String name, String color, Long id);

    Optional<Product> findByName(String name);

}
