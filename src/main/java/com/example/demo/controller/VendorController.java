package com.example.demo.controller;

import com.example.demo.dto.VendorRequest;
import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendor createVendor(@RequestBody VendorRequest request) {
        return vendorService.createVendor(request);
    }
}
