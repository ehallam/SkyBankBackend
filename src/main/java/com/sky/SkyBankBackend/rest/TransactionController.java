package com.sky.SkyBankBackend.rest;

import com.sky.SkyBankBackend.entities.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    public TransactionController() {}

    @GetMapping("/getAll")
    public List<CarWithOwnerDTO> getAllCars() {
        return this.service.getAllCars();
    }

    @GetMapping("/get/{id}")
    public CarWithOwnerDTO getCarById(@PathVariable int id) {
        return this.service.getCarById(id);
    }

    @GetMapping("/get/name/{make}")
    public CarWithOwnerDTO getCarByMake(@PathVariable String make) {
        return this.service.getCarByMake(make);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CarWithOwnerDTO addCar(@RequestBody Car newCar) {
        return this.service.addCar(newCar);
    }

    @PutMapping("/update/{id}")
    public CarWithOwnerDTO updateCar(@RequestBody Car newCar, @PathVariable int id) {
        return this.service.updateCar(newCar, id);
    }

    @DeleteMapping("/remove/{id}")
    public CarWithOwnerDTO removeById(@PathVariable int id) {
        return this.service.removeById(id);
    }
}
