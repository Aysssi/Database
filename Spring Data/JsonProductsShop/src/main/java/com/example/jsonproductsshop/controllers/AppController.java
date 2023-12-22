package com.example.jsonproductsshop.controllers;

import com.example.jsonproductsshop.constants.GlobalConstants;
import com.example.jsonproductsshop.domain.dtos.CategorySeedDto;
import com.example.jsonproductsshop.domain.dtos.ProductSeedDto;
import com.example.jsonproductsshop.domain.dtos.UserSeedRto;
import com.example.jsonproductsshop.domain.service.CategoryService;
import com.example.jsonproductsshop.domain.service.ProductInRangeDto;
import com.example.jsonproductsshop.domain.service.ProductService;
import com.example.jsonproductsshop.domain.service.UserService;
import com.example.jsonproductsshop.util.FileUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Component
public class AppController implements CommandLineRunner {
    private final Gson gson;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ProductService productService;
    private final FileUtil fileUtil;
    

    @Autowired
    public AppController(Gson gson, CategoryService categoryService, UserService userService, ProductService productService, FileUtil fileUtil) {
        this.gson = gson;
        this.categoryService = categoryService;
        this.userService = userService;
        this.productService = productService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedCategories();
        this.seedUsers();
        this.seedProducts();


    //    this.writeProductInRange();
    }

//    private void writeProductInRange() {
//        List<ProductInRangeDto> dtos = this.productService.getAllProductInRange();
//
//        String json = this.gson.toJson(dtos);
//        this.fileUtil.write(json,GlobalConstants.EX_1_OUTPUT);
//
//    }

    private void seedProducts() throws FileNotFoundException {
        ProductSeedDto[] dtos = this.gson
                .fromJson(new FileReader(GlobalConstants.PRODUCTS_FILE_PATH),
                        ProductSeedDto[].class);

        this.productService.seedProducts(dtos);
    }

    private void seedUsers() throws FileNotFoundException {
        UserSeedRto [] dtos = this.gson
                .fromJson(new FileReader(GlobalConstants.USERS_FILE_PATH),
                        UserSeedRto[].class);
       this.userService.seedUsers(dtos);
    }

    private void seedCategories() throws FileNotFoundException {

        CategorySeedDto[] dtos = this.gson
                .fromJson(new FileReader(GlobalConstants.CATEGORIES_FILE_PATH),
                        CategorySeedDto[].class);

        this.categoryService.seedCategories(dtos);
    }
}
