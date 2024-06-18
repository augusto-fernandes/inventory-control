package br.com.inventorycontrol.mapper;

import br.com.inventorycontrol.model.Product;
import br.com.inventorycontrol.model.User;
import br.com.inventorycontrol.model.dto.ProductDto;
import br.com.inventorycontrol.model.dto.UserDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-18T00:08:40-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 22.0.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product toEntity(ProductDto productDto) {
        if ( productDto == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( productDto.getId() );
        product.name( productDto.getName() );
        product.description( productDto.getDescription() );
        product.quantity( productDto.getQuantity() );
        product.value( productDto.getValue() );
        product.user( userDtoToUser( productDto.getUser() ) );

        return product.build();
    }

    @Override
    public ProductDto toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDto.ProductDtoBuilder productDto = ProductDto.builder();

        productDto.id( product.getId() );
        productDto.name( product.getName() );
        productDto.description( product.getDescription() );
        productDto.quantity( product.getQuantity() );
        productDto.value( product.getValue() );
        productDto.user( userToUserDto( product.getUser() ) );

        return productDto.build();
    }

    protected User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDto.getId() );

        return user.build();
    }

    protected UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );

        return userDto.build();
    }
}
