package com.example.cardealerjson.domain.services.impl;

import com.example.cardealerjson.domain.dtos.implDto.PartSeedDto;
import com.example.cardealerjson.domain.entities.Part;
import com.example.cardealerjson.domain.entities.Supplier;
import com.example.cardealerjson.domain.repositories.PartRepository;
import com.example.cardealerjson.domain.repositories.SupplierRepository;
import com.example.cardealerjson.domain.services.PartService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final static  String PARTS_PATH ="src/main/resources/json/parts.json";
    private final SupplierRepository supplierRepository;

    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    @Autowired
    public PartServiceImpl(SupplierRepository suppliersRepository, SupplierRepository supplierRepository, PartRepository partRepository, ModelMapper modelMapper, Gson gson) {
        this.supplierRepository = supplierRepository;

        this.partRepository = partRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }


    @Override
    public void seedParts() throws Exception {
       String  content = String.join("", Files.readAllLines(Path.of(PARTS_PATH)));

        PartSeedDto[] partSeedDtos = this.gson.fromJson(content, PartSeedDto[].class);
        for (PartSeedDto partSeedDto : partSeedDtos) {
            Part part = this.modelMapper.map(partSeedDto, Part.class);
  //        part.setSupplier(getRandomSupplier());

            this.partRepository.saveAndFlush(part);
        }
    }

    private Supplier getRandomSupplier() throws Exception {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.supplierRepository.count()) +1;
     Optional<Supplier> supplier = this.supplierRepository.findById(index);

        if (supplier.isPresent()){
            return supplier.get();

        }else {
            throw new Exception("Supplier don't exist");
        }

    }

}
