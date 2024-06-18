package br.com.inventorycontrol.mapper;

import br.com.inventorycontrol.model.User;
import br.com.inventorycontrol.model.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User toEntity(UserDto userDtoto);
    UserDto toDto(User user);
}
