package br.com.inventorycontrol.mapper;

import br.com.inventorycontrol.model.User;
import br.com.inventorycontrol.model.dto.UserDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-19T17:58:14-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 22.0.1 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto userDtoto) {
        if ( userDtoto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( userDtoto.getId() );

        return user.build();
    }

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );

        return userDto.build();
    }
}
