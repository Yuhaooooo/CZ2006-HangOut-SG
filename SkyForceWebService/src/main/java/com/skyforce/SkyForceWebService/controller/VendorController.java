package com.skyforce.SkyForceWebService.controller;


import com.skyforce.SkyForceWebService.model.Vendor;
import com.skyforce.SkyForceWebService.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VendorController {

    // TODO:  Change http status for different exceptions

    @Autowired
    VendorService vendorService;
    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/vendor")
    public String getAllVendors() {
        List<Vendor> vendors = vendorService.findAll();
        return "there are a brunch of vendors " + vendors;
    }

    @GetMapping("/vendor/{id}")
    public String getVendorById(@PathVariable("id") Long id) {
        Vendor vendor = vendorService.findVendorById(id);
        return "find vendor by id: " + vendor;
    }

    @PostMapping("/vendor")
    @ResponseStatus(HttpStatus.CREATED)
    public String createVendor(@Valid @RequestBody Vendor vendor) {
        vendor.setId(nextId.incrementAndGet());
        return "post success" + vendorService.save(vendor);
    }

    @PutMapping("/vendor/{id}")
    public String updateVendorById(@PathVariable("id") Long id, @Valid @RequestBody Vendor vendor) {

        Vendor oldVendor = vendorService.findVendorById(id);
        oldVendor.setFirstName(vendor.getFirstName());
        oldVendor.setLastName(vendor.getLastName());
        oldVendor.setGender(vendor.getGender());
        oldVendor.setShops(vendor.getShops());
        Vendor updatedVendor = vendorService.save(oldVendor);
        return "updated Vendor" + updatedVendor;
    }

    @DeleteMapping("/vendor/{id}")
    public ResponseEntity<?> deleteVendor(@PathVariable(value = "id") Long id) {
        Vendor vendor = vendorService.findVendorById(id);
        vendorService.delete(vendor);
        return ResponseEntity.ok().build();
    }
}