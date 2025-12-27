package com.example.demo.service;

import com.example.demo.model.Vendor;
import java.util.List;

public interface VendorService {
    Vendor createVendor(Vendor vendor); [cite: 249]
    Vendor getVendor(Long id); [cite: 250]
    List<Vendor> getAllVendors(); [cite: 251]
}