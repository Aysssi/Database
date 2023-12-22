package com.example.jsonproductsshop.domain.service;

import com.example.jsonproductsshop.domain.dtos.ProductSeedDto;

import java.util.List;

public interface ProductService {
    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto>  getAllProductInRange();
}
