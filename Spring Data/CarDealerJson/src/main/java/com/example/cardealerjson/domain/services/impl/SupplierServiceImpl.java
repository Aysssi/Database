package com.example.cardealerjson.domain.services.impl;

import com.example.cardealerjson.domain.dtos.implDto.SupplierSeedDto;
import com.example.cardealerjson.domain.entities.Supplier;
import com.example.cardealerjson.domain.repositories.SupplierRepository;
import com.example.cardealerjson.domain.services.SupplierService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class SupplierServiceImpl implements SupplierService {
    private  final static  String SUPPLIER_PATH = "src/main/resources/json/suppliers.json";
    private final SupplierRepository suppliersRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;


     @Autowired
    public SupplierServiceImpl(SupplierRepository suppliersRepository, ModelMapper modelMapper, Gson gson) {
        this.suppliersRepository = suppliersRepository;
        this.modelMapper = modelMapper;
         this.gson = gson;
     }

    @Override
    public void seedSupplier() throws IOException {
          String content = String.join("", Files.readAllLines(Path.of(SUPPLIER_PATH)));

        SupplierSeedDto[] supplierSeedDtos = this.gson.fromJson(content, SupplierSeedDto[].class);

        for (SupplierSeedDto supplierSeedDto : supplierSeedDtos) {
            this.suppliersRepository.saveAndFlush (this.modelMapper.map(supplierSeedDto, Supplier.class));

        }



    }
}
