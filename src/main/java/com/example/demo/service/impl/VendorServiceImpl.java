// package com.example.demo.service.impl;

// import com.example.demo.model.Vendor;
// import com.example.demo.repository.VendorRepository;
// import com.example.demo.exception.ResourceNotFoundException;
// import com.example.demo.service.VendorService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;

// @Service
// public class VendorServiceImpl implements VendorService {

//     @Autowired
//     private VendorRepository vendorRepository;

//     @Override
//     public Vendor createVendor(Vendor vendor) {
//         if (vendorRepository.existsByName(vendor.getVendorName())) {
//             throw new IllegalArgumentException("Vendor name must be unique");
//         }
//         return vendorRepository.save(vendor);
//     }

//     @Override
//     public Vendor getVendor(Long id) {
//         return vendorRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with ID: " + id));
//     }

//     @Override
//     public List<Vendor> getAllVendors() {
//         return vendorRepository.findAll();
//     }
// }
