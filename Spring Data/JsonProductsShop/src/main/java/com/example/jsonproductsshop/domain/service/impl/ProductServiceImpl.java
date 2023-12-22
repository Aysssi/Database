package com.example.jsonproductsshop.domain.service.impl;

import com.example.jsonproductsshop.domain.dtos.ProductSeedDto;
import com.example.jsonproductsshop.domain.entities.Product;
import com.example.jsonproductsshop.domain.repositories.ProductRepository;
import com.example.jsonproductsshop.domain.service.CategoryService;
import com.example.jsonproductsshop.domain.service.ProductInRangeDto;
import com.example.jsonproductsshop.domain.service.ProductService;
import com.example.jsonproductsshop.domain.service.UserService;
import com.example.jsonproductsshop.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
     private final ModelMapper modelMapper;
     private final ValidatorUtil validatorUtil;
     private final ProductRepository productRepository;
     private final UserService userService;
     private final CategoryService categoryService;

     @Autowired

    public ProductServiceImpl(ModelMapper modelMapper, ValidatorUtil validatorUtil, ProductRepository productRepository, UserService userService, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.validatorUtil = validatorUtil;
        this.productRepository = productRepository;
         this.userService = userService;
         this.categoryService = categoryService;
     }


    @Override

    public void seedProducts(ProductSeedDto[] productSeedDtos) {

        if (this.productRepository.count() != 0){
            return;
        }

        Arrays.stream(productSeedDtos)
                .forEach(productSeedDto -> {
                    if (this.validatorUtil.isValid(productSeedDto)){

                        Product product = this.modelMapper
                                .map(productSeedDto,Product.class);


                         product.setSeller(this.userService.getRandomUser());

                        Random random = new Random();
                        int randomNum = random.nextInt(2);
                        if (randomNum == 1){
                          product.setBuyer(this.userService.getRandomUser());
                        }
                        product.setCategories(new HashSet<>(this.categoryService.getRandomCategories()));
                         this.productRepository.saveAndFlush(product);

                    }else {
                        this.validatorUtil.violations(productSeedDto)
                                .stream()
                                .map(ConstraintViolation::getMessage)
                                .forEach(System.out::println);
                    }
                });
    }

    @Override
    public List<ProductInRangeDto> getAllProductInRange() {


        return this.productRepository.findAllByPriceBetweenAndAndBuyerIsNull(BigDecimal.valueOf(500),BigDecimal.valueOf(1000))
                .stream()
                .map(product -> {
                    ProductInRangeDto productInRangeDto = this.modelMapper.map(product,ProductInRangeDto.class);

                    productInRangeDto.setSeller(String.format("%s %s", product.getSeller().getFirstName(),
                            product.getSeller().getLastName()));
                    return productInRangeDto;

                }).collect(Collectors.toList());
    }
}
