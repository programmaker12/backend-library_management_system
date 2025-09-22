package com.thinkinnovative.library_management_system.controller;


import com.thinkinnovative.library_management_system.entity.CloudVendor;
import com.thinkinnovative.library_management_system.exception.CloudVendorNotFoundException;
import com.thinkinnovative.library_management_system.service.CloudVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudservice")
public class CloudAPIController {


    public CloudAPIController(CloudVendorService vendorService) {
        this.vendorService = vendorService;
    }

    //CloudVendor cloudVendor;
    CloudVendorService vendorService;
    @GetMapping("/{vendorId}")
    public ResponseEntity<Object> cloudVendor(@PathVariable String vendorId) {
          try {
              CloudVendor vendor = vendorService.getVendorById(vendorId);
              return new ResponseEntity<>(vendor, HttpStatus.OK);
          }
          catch (CloudVendorNotFoundException e)
          {
              return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
          }

    }
    @GetMapping("/allVendors")
    public List<CloudVendor> cloudVendor(){

        return vendorService.allVendor();
        //new CloudVendor("A1", "Arun", "Amravati", "821514218");
    }
    @PostMapping("/create")
    public String createVendor(@RequestBody CloudVendor cloudVendor)
    {
        // CloudVendor cloudVendor;


        //this.cloudVendor = cloudVendor;
        return vendorService.createVendor(cloudVendor);
    }
    @PutMapping("/update")
    public String updateVendor(@RequestBody CloudVendor cloudVendor)
    {
        // CloudVendor cloudVendor;
        //this.cloudVendor = cloudVendor;
        return vendorService.updateVendor(cloudVendor);
    }

    @PutMapping("/deactivate/{vendorId}")
    public String deactivateVendor(@PathVariable String vendorId, @RequestBody Map<String, String> requestBody) {
        CloudVendor vendor = vendorService.getVendorById(vendorId);
        System.out.println(vendor.getVendorId());
//        vendor.setActive(cloudVendor.isActive());
//        vendorService.updateVendor(vendor);
//        return "Vendor deactivated successfully";
        // Check if `isActive` key is present in the request body
        if (requestBody.containsKey("isActive")) {
            vendor.setActive(requestBody.get("isActive")); // Update active status
            vendorService.updateVendor(vendor); // Save updated vendor
            return "The vendor is now " + ("1".equals(vendor.isActive()) ? "active" : "inactive");
        } else {
            return "Invalid request: Missing 'isActive' field";
        }
    }


    @DeleteMapping("/{vendorId}")
    public String deleteVendor(@PathVariable String vendorId)
    {
        // CloudVendor cloudVendor;
        //this.cloudVendor.setVendorAddress(null);
        return vendorService.deleteVendor(vendorId);
    }
}




