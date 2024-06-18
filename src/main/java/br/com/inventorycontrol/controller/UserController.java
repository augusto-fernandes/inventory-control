package br.com.inventorycontrol.controller;

import br.com.inventorycontrol.mapper.UserMapper;
import br.com.inventorycontrol.model.User;
import br.com.inventorycontrol.model.dto.UserDto;
import br.com.inventorycontrol.service.UserService;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id){
        try{
            User user = userService.findById(id);
            UserDto userDto = userMapper.toDto(user);
            return ResponseEntity.ok(userDto);
        }catch (NoResultException noResultException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/")
    public ResponseEntity<UserDto> putUser(@RequestBody @Valid UserDto userDto){
        return salvarDados(userDto, true);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> postUser(@RequestBody @Valid UserDto userDto ){
        return salvarDados(userDto, false);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<UserDto> salvarDados(UserDto userDto, boolean update) {
        try{
            User user = userMapper.toEntity(userDto);
            userService.save(user);
            return update ?  ResponseEntity.status(HttpStatus.NO_CONTENT).body(userDto) : ResponseEntity.status(HttpStatus.CREATED).body(userDto) ;
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}