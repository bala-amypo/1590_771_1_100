package com.example.demo.controller;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    @PostMapping
    public Vendor createVendor(@RequestBody Vendor vendor) {
        return vendorService.createVendor(vendor);
    }

    @GetMapping("/{id}")
    public Vendor getVendor(@PathVariable Long id) {
        return vendorService.getVendor(id);
    }

    @GetMapping
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }
}