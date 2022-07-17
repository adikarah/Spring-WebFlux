package com.spring.webfluxdemo.service;

import com.spring.webfluxdemo.dto.MultiplyRequestDto;
import com.spring.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        /*
         * we should use fromSupplier for performing the calculation
         * in reactive state, this will do it lazily as possible
         * If we wanted to return input as it is then we can use
         * Mono.just otherwise for all others operations use supplier
         */
        return Mono.fromSupplier(() -> input * input)
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1, 10)
                .delayElements(Duration.ofSeconds(1)) // this will stop the service immediately without any delay
//                .doOnNext(i -> SleepUtil.sleepSeconds(1)) // this will do little delay
                .doOnNext(i -> System.out.println("Reactive math-service processing: " + i))
                .map(i -> new Response(i * input));
    }

    public Mono<Response> multiply(Mono<MultiplyRequestDto> multiplyRequestDtoMono) {
        return multiplyRequestDtoMono
                .map(dto -> dto.getFirstNumber() * dto.getSecondNumber())
                .map(Response::new);
    }
}
