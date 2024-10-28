package tpi.backend.e_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tpi.backend.e_commerce.dto.stockEntryDto.CreateStockEntryDto;

import tpi.backend.e_commerce.services.stock.interfaces.ISaveStockService;

@RestController
@RequestMapping("/stock-entry")
@CrossOrigin(origins = "http://localhost:5173") 
public class StockEntryController {

    @Autowired
    private ISaveStockService saveStockService;
    
    @PostMapping
    public ResponseEntity<?> saveStockEntry(
        @RequestBody CreateStockEntryDto stockEntryDto, @Valid BindingResult result) {

        return saveStockService.saveStockEntry(stockEntryDto, result);
    }
}
