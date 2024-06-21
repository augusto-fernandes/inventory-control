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

    private final UserService service;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> show(@PathVariable("id") Long id){
        try{
            User user = service.findById(id);
            UserDto userDto = mapper.toDto(user);
            return ResponseEntity.ok(userDto);
        }catch (NoResultException noResultException){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto){
        return save(userDto, true);
    }

    @PostMapping("/create")
    public ResponseEntity<UserDto> store( ){
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(service.save(new User())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity<UserDto> save(UserDto userDto, boolean update) {
        try{
            User user = mapper.toEntity(userDto);
            service.save(user);
            return update ?  ResponseEntity.status(HttpStatus.NO_CONTENT).body(userDto) : ResponseEntity.status(HttpStatus.CREATED).body(userDto) ;
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
