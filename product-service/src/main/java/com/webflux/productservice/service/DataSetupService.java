package com.webflux.productservice.service;

import com.webflux.productservice.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DataSetupService implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) {
        ProductDto product1 = new ProductDto("4K-TV", 70000);
        ProductDto product2 = new ProductDto("iPhone", 75000);
        ProductDto product3 = new ProductDto("SLR Camera", 110000);
        ProductDto product4 = new ProductDto("HeadPhone", 5000);

        /*
         * here nobody will subscribe this since
         * this is not part of controller
         * that's why we need to subscribe it
         */
        Flux.just(product1, product2, product3, product4)
                //since map will return Flux<Mono<ProductDto>> object that's why we're
                //using flatMap here to return object in proper format Flux<ProductDto>
//                .map(productDto -> productService.insertProduct(Mono.just(productDto)))
                .flatMap(productDto -> productService.insertProduct(Mono.just(productDto)))
                .subscribe(productDto -> System.out.println(productDto));
    }
}
