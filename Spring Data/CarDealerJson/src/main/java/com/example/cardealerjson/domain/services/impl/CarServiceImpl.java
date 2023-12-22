package com.example.cardealerjson.domain.services.impl;

import com.example.cardealerjson.domain.dtos.export.CarExportDto;
import com.example.cardealerjson.domain.dtos.implDto.CarSeedDto;
import com.example.cardealerjson.domain.entities.Car;
import com.example.cardealerjson.domain.entities.Part;
import com.example.cardealerjson.domain.repositories.CarRepository;
import com.example.cardealerjson.domain.repositories.PartRepository;
import com.example.cardealerjson.domain.services.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.file.Files;

import java.nio.file.Path;
import java.util.*;

@Service
public class CarServiceImpl implements CarService {
    private final static String CAR_PATH = "src/main/resources/json/cars.json";

    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;

    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository, ModelMapper modelMapper, Gson gson) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;

        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public void seedCars() throws Exception {

        String content = String.join("", Files.readAllLines(Path.of(CAR_PATH)));

        CarSeedDto [] carSeedDtos = this.gson.fromJson(content, CarSeedDto[].class);
        for (CarSeedDto carSeedDto : carSeedDtos) {
            Car car  = this.modelMapper.map(carSeedDto,Car.class);
        //    car.setParts(getRandomParts());

            this.carRepository.saveAndFlush(car);
        }

    }

    @Override
    public String findByToyota() {

        Set<Car> toyota = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc("Toyota");
        List<CarExportDto> carExportDtoList = new ArrayList<>();
        for (Car car : toyota) {
            CarExportDto carExportDto = this.modelMapper.map( car,CarExportDto.class);
            carExportDtoList.add(carExportDto) ;
        }
        return this.gson.toJson(carExportDtoList);
    }

    private List<Part> getRandomParts() throws Exception {
        List<Part>  parts = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Part part = this.getRandomPart();
            parts.add(part);
        }

      return parts;
    }

    private Part getRandomPart() throws Exception {
        Random random = new Random();
        long index = (long)  random.nextInt((int) this.partRepository.count()) +1;
        Optional<Part> part = this.partRepository.findById(index);

        if (part.isPresent()){
            return part.get();
        }else {
            throw new Exception("Invalid part id");
        }
    }
}
