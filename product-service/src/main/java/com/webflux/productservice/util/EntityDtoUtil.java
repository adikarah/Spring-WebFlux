package com.webflux.productservice.util;

import com.webflux.productservice.dto.ProductDto;
import com.webflux.productservice.entity.Product;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        /*
         * this will copy all the properties of product object to dto object
         * but this is little in-efficient
         */
        BeanUtils.copyProperties(product, dto);
        return dto;
    }

    public static Product toDto(ProductDto productDto) {
        Product product = new Product();
        /*
         * this will copy all the properties of product object to dto object
         * but this is little in-efficient
         */
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
