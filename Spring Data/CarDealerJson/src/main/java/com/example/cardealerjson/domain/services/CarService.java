package com.example.cardealerjson.domain.services;

import java.io.IOException;

public interface CarService {
   void seedCars() throws Exception;

   String findByToyota();
}
