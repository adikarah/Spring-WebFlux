package com.webflux.userservice.service;

import com.webflux.userservice.dto.UserDto;
import com.webflux.userservice.repository.UserRepository;
import com.webflux.userservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<UserDto> getAllUsers() {
        return userRepository.findAll()
                .map(EntityDtoUtil::toUserDto);
    }

    public Mono<UserDto> getUserById(final int userID) {
        return userRepository.findById(userID)
                .map(EntityDtoUtil::toUserDto);
    }

    public Mono<UserDto> addUser(Mono<UserDto> userDto) {
        return userDto.map(EntityDtoUtil::toUser)
                .flatMap(user -> userRepository.save(user))
                .map(EntityDtoUtil::toUserDto);
    }

    public Mono<UserDto> updateUser(Integer userID, Mono<UserDto> userDtoMono) {
        /*
        * map(user -> userDtoMono
                        .map(userDto -> EntityDtoUtil.toUser(userDto))
                        .doOnNext(e -> e.setId(userID)))
                        * will return Mono<Mono<User>>
                        * we wanted to save entity only
                        * so we have to use flatMap
         * map(userMono -> userRepository.save(userMono))
         * will return Mono<Mono<User>> and we wanted
         * Mono<User> to convert the user to userDto,
         * so we have to use flatMap
         */
        return userRepository.findById(userID)
                .flatMap(user -> userDtoMono
                        .map(userDto -> EntityDtoUtil.toUser(userDto))
                        .doOnNext(e -> e.setId(userID)))
                .flatMap(userMono -> userRepository.save(userMono))
                .map(userDto -> EntityDtoUtil.toUserDto(userDto));
    }

    public Mono<Void> deleteUser(Integer userID) {
        return userRepository.deleteById(userID);
    }
}
