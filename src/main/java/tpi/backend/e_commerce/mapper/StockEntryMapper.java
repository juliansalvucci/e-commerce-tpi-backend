package tpi.backend.e_commerce.mapper;

import tpi.backend.e_commerce.dto.stockEntryDto.CreateStockEntryDto;
import tpi.backend.e_commerce.dto.stockEntryDto.ResponseStockEntryDto;
import tpi.backend.e_commerce.models.StockEntry;

public class StockEntryMapper {
    
    public static StockEntry toEntity(CreateStockEntryDto stockEntryDto){
        return StockEntry
            .builder()
            .build();
    }

    public static ResponseStockEntryDto toDto(StockEntry stockEntry){

        return ResponseStockEntryDto
            .builder()
            .id(stockEntry.getId())
            .creationDatetime(stockEntry.getCreation_datetime())
            .stockEntryDetails(StockEntryDetailMapper.toDtoList(stockEntry.getStockEntryDetails()))
            .build();
    }
}
