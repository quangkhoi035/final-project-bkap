package com.khoithuong.controllers.restfullapi;

import com.khoithuong.entity.Address;
import com.khoithuong.entity.Order;
import com.khoithuong.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/address")
    public List<Address> getAllOrders() {
        return addressRepository.findAll();
    }
}
