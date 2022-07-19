package com.webflux.productservice.service;

import com.webflux.productservice.dto.ProductDto;
import com.webflux.productservice.repository.ProductRepository;
import com.webflux.productservice.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Flux<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .map(EntityDtoUtil::toDto);
        // can also be written as dto -> EntityDaoUtil.toDto(dto)
    }

    public Mono<ProductDto> findProductById(String id) {
        return productRepository.findById(id)
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> insertProduct(Mono<ProductDto> productDto) {
        return productDto.map(EntityDtoUtil::toProduct)
                .flatMap(dto -> productRepository.insert(dto))
                .map(EntityDtoUtil::toDto);
    }

    public Mono<ProductDto> updateProductDetails(String id, Mono<ProductDto> productDto) {
        /*
          here first we will check if any product exist with given id
          if it exists then first we will convert the productDto
          into product entity form, and then we will set the id in product
          entity, and then we will save the updated one and convert that
          into the form of productDto and will return that
         */
        if (productRepository.findById(id).block() != null) {
            return productRepository.findById(id)
                    .flatMap(product -> productDto.map(EntityDtoUtil::toProduct)
                            .doOnNext(e -> e.setId(id)))
                    .flatMap(product -> productRepository.save(product))
                    .map(EntityDtoUtil::toDto);
        } else throw new RuntimeException("No data found with given id: " + id);
    }
}
