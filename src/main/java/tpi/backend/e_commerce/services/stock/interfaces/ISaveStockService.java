package tpi.backend.e_commerce.services.stock.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import tpi.backend.e_commerce.dto.stockEntryDto.CreateStockEntryDto;

public interface ISaveStockService {
    
    ResponseEntity<?> saveStockEntry(CreateStockEntryDto stockEntryDto, BindingResult result);
}
