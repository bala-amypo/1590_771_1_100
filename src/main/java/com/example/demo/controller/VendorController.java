package com.example.demo.controller;

import com.example.demo.model.Vendor;
import com.example.demo.service.VendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@Tag(name = "Vendors", description = "Vendor management operations") // Matches OpenApiConfig tag
@SecurityRequirement(name = "bearerAuth") // Ensures Swagger sends the JWT for this controller
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService; //
    }

    @PostMapping
    @Operation(summary = "Create a new vendor", description = "Requires USER or ADMIN role")
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        // Ensure the service handles any unique constraint violations for vendorName
        return ResponseEntity.ok(vendorService.createVendor(vendor));
    }

    @GetMapping
    @Operation(summary = "Get all vendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors()); //
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get vendor by ID")
    public ResponseEntity<Vendor> getVendor(@PathVariable Long id) {
        // If the vendor is not found, the service should throw a ResourceNotFoundException
        return ResponseEntity.ok(vendorService.getVendor(id));
    }
}