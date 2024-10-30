package tpi.backend.e_commerce.services.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


import tpi.backend.e_commerce.dto.stockEntryDetailDto.CreateStockEntryDetailDto;
import tpi.backend.e_commerce.dto.stockEntryDto.CreateStockEntryDto;
import tpi.backend.e_commerce.mapper.StockEntryDetailMapper;
import tpi.backend.e_commerce.mapper.StockEntryMapper;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.models.StockEntry;
import tpi.backend.e_commerce.models.StockEntryDetail;
import tpi.backend.e_commerce.repositories.IProductRepository;
import tpi.backend.e_commerce.repositories.IStockEntryRepository;
import tpi.backend.e_commerce.services.stock.interfaces.ISaveStockService;
import tpi.backend.e_commerce.validation.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaveStockService implements ISaveStockService{
    
    @Autowired
    private IStockEntryRepository stockRepository;
    
    @Autowired
    private IProductRepository productRepository;
    
    @Autowired
    private Validation validation;

    @Override
    public ResponseEntity<?> saveStockEntry(CreateStockEntryDto stockEntryDto, BindingResult result) {

        if (result.hasFieldErrors()) {
            return validation.validate(result);
        }
        try {
            validateStockEntry(stockEntryDto);
        } catch (RuntimeException e) {
            return validation.validate(
                "productId",
                e.getMessage(),
                404
            );
        }
        
        StockEntry stockEntryToSave = createStockEntryDetails(stockEntryDto.getStockEntryDetails());

        stockRepository.save(stockEntryToSave);
        return ResponseEntity.ok(StockEntryMapper.toDto(stockEntryToSave));
    }
    
    private StockEntry createStockEntryDetails(List<CreateStockEntryDetailDto> stockEntryDetailDtos) {

        List<StockEntryDetail> StockEntryDetailsToSave = new ArrayList<>();
        StockEntry stockEntry = new StockEntry();

        stockEntryDetailDtos.forEach(stockEntryDetailDto -> {

            Optional<Product> optionalProduct = productRepository.findById(stockEntryDetailDto.getProductId());

            Product product = optionalProduct.get();
            product.setStock(product.getStock() + stockEntryDetailDto.getQuantity());

            StockEntryDetail stockEntryDetailToSave = StockEntryDetailMapper.toEntity(stockEntryDetailDto,stockEntry,product);

            StockEntryDetailsToSave.add(stockEntryDetailToSave);
        });
        stockEntry.setStockEntryDetails(StockEntryDetailsToSave);
        return stockEntry;
    }

    private void validateStockEntry(CreateStockEntryDto stockEntryDto) throws RuntimeException{
        stockEntryDto.getStockEntryDetails().forEach(stockEntryDetailDto -> {

           Optional<Product> optionalProduct = productRepository.findActiveById(stockEntryDetailDto.getProductId()); 
           if (optionalProduct.isEmpty()) {
               throw new RuntimeException("No existe un producto con id " + stockEntryDetailDto.getProductId() + " en la base de datos");
           }
        });   
    }
}
