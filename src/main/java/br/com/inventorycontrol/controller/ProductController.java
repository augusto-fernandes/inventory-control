package br.com.inventorycontrol.controller;

import br.com.inventorycontrol.mapper.ProductMapper;
import br.com.inventorycontrol.model.Product;
import br.com.inventorycontrol.model.dto.ProductDto;
import br.com.inventorycontrol.service.ProductService;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable("id") Long id){
        try{
            Product product = productService.findById(id);
            ProductDto productDto = productMapper.toDto(product);
            return ResponseEntity.ok(productDto);
        }catch (NoResultException noResultException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    ResponseEntity<List<ProductDto>> findAll(){
        List<Product> productList = productService.findAll();
        return ResponseEntity.ok(productList.stream().map(productMapper::toDto).collect(Collectors.toList()));
    }

    @PutMapping("/")
    public ResponseEntity<ProductDto> putUser(@RequestBody @Valid ProductDto productDto){
        return salvarDados(productDto, true);
    }

    @PostMapping("/")
    public ResponseEntity<ProductDto> postUser(@RequestBody @Valid ProductDto productDto ){
        return salvarDados(productDto, false);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<ProductDto> salvarDados(ProductDto productDto, boolean update) {
        try{
            Product product = productMapper.toEntity(productDto);
           product = productService.save(product);
            return update ?  ResponseEntity.status(HttpStatus.NO_CONTENT).body(productDto) : ResponseEntity.status(HttpStatus.CREATED).body(productDto) ;
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
