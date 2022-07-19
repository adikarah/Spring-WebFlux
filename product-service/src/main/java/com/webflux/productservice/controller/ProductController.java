package com.webflux.productservice.controller;

import com.webflux.productservice.dto.ProductDto;
import com.webflux.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(path = "/all")
    public Flux<ProductDto> findAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "id/{id}")
    public Mono<ResponseEntity<ProductDto>> findProductWithId(@PathVariable String id) {
        return productService.findProductById(id)
//                .map(productDto -> ResponseEntity.ok(productDto))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(path = "add")
    public Mono<ProductDto> insertProduct(@RequestBody Mono<ProductDto> productDto) {
        return productService.insertProduct(productDto);
    }

    @PutMapping(path = "update/{id}")
    public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id, @RequestBody Mono<ProductDto> productDto) {
        return productService.updateProductDetails(id, productDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteProduct(id);
    }
}
