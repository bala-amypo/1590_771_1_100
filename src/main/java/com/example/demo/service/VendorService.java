package com.example.demo.service;

import com.example.demo.dto.VendorRequest;
import com.example.demo.model.Vendor;

public interface VendorService {
    Vendor createVendor(VendorRequest request);
}
