package com.example.demo.service.impl;

import com.example.demo.dto.VendorRequest;
import com.example.demo.model.Vendor;
import com.example.demo.repository.VendorRepository;
import com.example.demo.service.VendorService;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor createVendor(VendorRequest request) {

        Vendor vendor = new Vendor();
        vendor.setVendorName(request.getVendorName());
        vendor.setEmail(request.getEmail());
        vendor.setPhone(request.getPhone());
        vendor.setIndustry(request.getIndustry());

        return vendorRepository.save(vendor);
    }
}
