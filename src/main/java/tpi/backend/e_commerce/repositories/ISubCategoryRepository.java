package tpi.backend.e_commerce.repositories;
import java.util.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import tpi.backend.e_commerce.models.SubCategory;




public interface ISubCategoryRepository extends CrudRepository<SubCategory,Long>{
    @Query("select c from SubCategory c where c.deleted = false")
    List<SubCategory> findAllActive(); 
    //Busca todos las categorias que no han sido eliminados

    @Query("select c from SubCategory c where c.deleted = true")
    List<SubCategory> findAllDeleted();
    
    @Query("select c from SubCategory c where c.id = ?1 and c.deleted = false")
    Optional<SubCategory> findActiveById(Long id);
    //Solo devolvera la categoria si este no esta eliminado
    
    @Query("select c from SubCategory c where c.id = ?1 and c.deleted = true")
    Optional<SubCategory> findDeletedById(Long id);
    //Solo traera la categoria si este esta eliminado

    Optional<SubCategory> findByName(String name);

    @Query("select CASE when COUNT(p)>0 then true else false end from Product p where p.subCategory.id = ?1")
    boolean hasSubCategoryProducts(Long id);
}

