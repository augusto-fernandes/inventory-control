package br.com.inventorycontrol.mapper;

import br.com.inventorycontrol.model.Product;
import br.com.inventorycontrol.model.dto.ProductDto;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper {
    Product toEntity(ProductDto productDto);
    ProductDto toDto(Product product);
}
