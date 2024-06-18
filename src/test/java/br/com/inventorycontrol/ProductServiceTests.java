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
        when(productRepository.findById(1L)).thenReturn(productOptional);
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
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> response = productService.findAll();
        assertNotNull(response);
        assertEquals(1,response.size());
        assertEquals(Product.class, response.getFirst().getClass());

        assertNotNull(response.getFirst().getId());
        assertNotNull(response.getFirst().getName());
        assertNotNull(response.getFirst().getDescription());
    }

    private void startProduct() {
        user = User.builder().id(UUID.randomUUID()).build();
        product = Product.builder().id(1L).name("Coca-Cola").description("Refrigerante").quantity(10L).value(new BigDecimal(100)) .build();
        productOptional = Optional.of(Product.builder().id(1L).name("Coca-Cola").description("Refrigerante").quantity(10L).value(new BigDecimal(100)) .build());
        productDto = ProductDto.builder().id(1L).name("Coca-Cola").description("Refrigerante").quantity(10L).value(new BigDecimal(100)) .build();

    }
}
