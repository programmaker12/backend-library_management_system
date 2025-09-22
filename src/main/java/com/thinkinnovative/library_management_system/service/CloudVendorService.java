package com.thinkinnovative.library_management_system.service;

import com.thinkinnovative.library_management_system.entity.CloudVendor;

import java.util.List;

public interface CloudVendorService {
    public String createVendor(CloudVendor cloudVendor);
    public  String updateVendor(CloudVendor cloudVendor);
    public  String deleteVendor(String vendorId);
    public  CloudVendor getVendorById(String vendorId);
    public List<CloudVendor> allVendor();
}
