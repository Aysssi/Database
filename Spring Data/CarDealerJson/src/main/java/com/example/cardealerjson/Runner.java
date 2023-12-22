package com.example.cardealerjson;

import com.example.cardealerjson.domain.services.CarService;
import com.example.cardealerjson.domain.services.CustomerService;
import com.example.cardealerjson.domain.services.PartService;
import com.example.cardealerjson.domain.services.SupplierService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;




    public Runner(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;

        this.customerService = customerService;

    }

    @Override
    public void run(String... args) throws Exception {
//
//        this.supplierService.seedSupplier();
//        this.partService.seedParts();
//        this.carService.seedCars();
//       this.customerService.seedCustomer();

        System.out.println(this.carService.findByToyota());

    }
}
