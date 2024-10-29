package tpi.backend.e_commerce.repositories;

import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.StockEntry;

public interface IStockEntryRepository extends CrudRepository<StockEntry,Long>{
    
    
}
