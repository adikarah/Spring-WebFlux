package com.webflux.userservice.controller;

import com.webflux.userservice.dto.UserDto;
import com.webflux.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public Flux<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "id/{id}")
    public Mono<ResponseEntity<UserDto>> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "/add")
    public Mono<UserDto> addUser(@RequestBody Mono<UserDto> userDto) {
        return userService.addUser(userDto);
    }

    @PutMapping(path = "/update/id/{id}")
    public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable Integer userID, @RequestBody Mono<UserDto> userDtoMono) {
        return userService.updateUser(userID, userDtoMono)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "delete/{id}")
    public Mono<Void> deleteUser(@PathVariable Integer userID) {
        return userService.deleteUser(userID);
    }
}
