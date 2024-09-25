package tpi.backend.e_commerce.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.Brand;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.models.Product;


public interface ICategoryRepository extends CrudRepository<Category,Long>{
    @Query("select c from Category c where c.deleted = false")
    List<Category> findAllActive(); 
    //Busca todos las categorias que no han sido eliminados


    @Query("select c from Category c where c.deleted = true")
    List<Category> findAllDeleted();
    
    @Query("select c from Category c where c.id = ?1 and c.deleted = false")
    Optional<Category> findActiveById(Long id);
    //Solo devolvera la categoria si este no esta eliminado
    
    @Query("select c from Category c where c.id = ?1 and c.deleted = true")
    Optional<Category> findDeletedById(Long id);
    //Solo traera la categoria si este esta eliminado
    

    @Query("select CASE when COUNT(c) > 0 then true else false end from Category c where UPPER(c.name) = UPPER(?1)")
    boolean existsByName(String name);

    @Query("select CASE when COUNT(c) > 0 then true else false end from Category c where UPPER(c.name) = UPPER(?1) and c.id <> ?2")
    boolean existsByNameExceptId(String name, Long id);

    Optional<Category> findByName(String name);

    @Query("select CASE when COUNT(p)>0 then true else false end from Product p where p.brand.id = ?1")
    boolean hasCategorySubCategorys(Long id);

}
