package com.example.cardealerjson.domain.services.impl;

import com.example.cardealerjson.domain.dtos.implDto.CustomerSeedDto;
import com.example.cardealerjson.domain.entities.Customer;
import com.example.cardealerjson.domain.repositories.CustomerRepository;
import com.example.cardealerjson.domain.services.CustomerService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final static String CUSTOMER_PATH = "src/main/resources/json/customers.json";
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper, Gson gson) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;


        this.gson = gson;
    }

    @Override
    public void seedCustomer() throws IOException {
         String content = String.join("", Files.readAllLines(Path.of(CUSTOMER_PATH)));

        CustomerSeedDto[] customerSeedDtos = this.gson.fromJson(content, CustomerSeedDto[].class);
        for (CustomerSeedDto customerSeedDto : customerSeedDtos) {
            Customer customer = this.modelMapper.map(customerSeedDto,Customer.class);
            this.customerRepository.saveAndFlush(customer);

        }

    }
}
