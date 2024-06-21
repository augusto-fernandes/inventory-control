package br.com.inventorycontrol.controller;

import br.com.inventorycontrol.mapper.ProductMapper;
import br.com.inventorycontrol.model.Product;
import br.com.inventorycontrol.model.dto.ProductDto;
import br.com.inventorycontrol.service.ProductService;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> show(@PathVariable("id") Long id){
        try{
            Product product = service.findById(id);
            ProductDto productDto = mapper.toDto(product);
            return ResponseEntity.ok(productDto);
        }catch (NoResultException noResultException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Page<ProductDto>> index(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = service.findAll(pageable);
        Page<ProductDto> productDtoPage = productPage.map(mapper::toDto);
        return ResponseEntity.ok(productDtoPage);
    }

    @PutMapping("/")
    public ResponseEntity<ProductDto> update(@RequestBody @Valid ProductDto productDto){
        return save(productDto, true);
    }

    @PostMapping("/create")
    public ResponseEntity<ProductDto> store(@RequestBody @Valid ProductDto productDto ){
        return save(productDto, false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<ProductDto> save(ProductDto productDto, boolean update) {
        try{
            Product product = mapper.toEntity(productDto);
           product = service.save(product);
            return update ?  ResponseEntity.status(HttpStatus.NO_CONTENT).body(productDto) : ResponseEntity.status(HttpStatus.CREATED).body(productDto) ;
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
