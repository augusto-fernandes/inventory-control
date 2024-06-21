package br.com.inventorycontrol;

import br.com.inventorycontrol.mapper.ProductMapper;
import br.com.inventorycontrol.model.Product;
import br.com.inventorycontrol.model.User;
import br.com.inventorycontrol.model.dto.ProductDto;
import br.com.inventorycontrol.repository.ProductRepository;
import br.com.inventorycontrol.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTests {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    private Product product;
    private ProductDto productDto;
    private Optional<Product> productOptional;
    private User user;


    @BeforeEach
    public  void setUp(){
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository);
        startProduct();
    }
    @Test
    void whenFindByIdThenReturnAnProductInstance() {
        // forma sem o import statico
        // Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(optionalUser);
        when(productRepository.findById(1L)).thenReturn(this.productOptional);
        Product response = productService.findById(1L);
        productDto = productMapper.toDto(product);

        assertNotNull(response);
        assertEquals(Product.class, response.getClass());
        assertEquals(productDto.getId(), response.getId());
        assertEquals(productDto.getName(), response.getName());
        assertEquals(productDto.getDescription(), response.getDescription());
    }

    @Test
    void whenFindAllThenReturnAnListOfProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> productPage = new PageImpl<>(List.of(product), pageable, 1);


        when(productRepository.findAll(pageable)).thenReturn(productPage);

        Page<Product> response = productService.findAll(pageable);

        assertNotNull(response);
        assertEquals(1, response.getTotalElements());
        assertEquals(Product.class, response.getContent().get(0).getClass());

        assertNotNull(response.getContent().getFirst().getId());
        assertNotNull(response.getContent().getFirst().getName());
        assertNotNull(response.getContent().getFirst().getDescription());
    }

    private void startProduct() {
        user = User.builder().id(UUID.randomUUID()).build();
        product = Product.builder().id(1L).name("Coca-Cola").description("Refrigerante").quantity(10L).value(new BigDecimal(100)) .build();
        productOptional = Optional.of(Product.builder().id(1L).name("Coca-Cola").description("Refrigerante").quantity(10L).value(new BigDecimal(100)) .build());
        productDto = ProductDto.builder().id(1L).name("Coca-Cola").description("Refrigerante").quantity(10L).value(new BigDecimal(100)) .build();

    }
}
