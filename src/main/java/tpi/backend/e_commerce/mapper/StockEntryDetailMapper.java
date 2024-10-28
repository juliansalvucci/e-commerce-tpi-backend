package tpi.backend.e_commerce.mapper;

import java.util.List;

import tpi.backend.e_commerce.dto.stockEntryDetailDto.CreateStockEntryDetailDto;
import tpi.backend.e_commerce.dto.stockEntryDetailDto.ResponseStockEntryDetailDto;
import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.models.StockEntry;
import tpi.backend.e_commerce.models.StockEntryDetail;

public class StockEntryDetailMapper {
    
    public static StockEntryDetail toEntity(CreateStockEntryDetailDto stockEntryDetailDto, StockEntry stockEntry, Product product){
        return StockEntryDetail
            .builder()
            .quantity(stockEntryDetailDto.getQuantity())
            .stockEntry(stockEntry)
            .product(product)
            .build();
    }

    public static ResponseStockEntryDetailDto toDto(StockEntryDetail stockEntryDetail){
        return ResponseStockEntryDetailDto
            .builder()
            .id(stockEntryDetail.getId())
            .productId(stockEntryDetail.getProduct().getId())
            .quantity(stockEntryDetail.getQuantity())
            .build();
    }

    public static List<ResponseStockEntryDetailDto> toDtoList(List<StockEntryDetail> stockEntryDetails){
        return stockEntryDetails.stream()
        .map(stockEntryDetail -> StockEntryDetailMapper.toDto(stockEntryDetail)).toList();
    }
}
