package org.example.controller;

import org.example.Device;
import org.example.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    // In DeviceController.java

    // Add Device (POST)
    @PostMapping
    public Device createDevice(@RequestBody Device device) {
        // You need to call the service to save the device
        return deviceService.addDevice(device); // [MODIFIED LINE]
    }

    // Get All Devices (GET)
    @GetMapping
    public List<Device> getDevices() {
        return deviceService.getAllDevices();
    }
}
