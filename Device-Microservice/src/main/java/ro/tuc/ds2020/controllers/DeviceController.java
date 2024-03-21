package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.services.DeviceService;


import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(value="/getDevices/{id}")
    public List<Device> getDevices(@PathVariable("id") Long userId) {
        List<Device> dtos = deviceService.findDevicesForUser(userId);
//        for (DeviceDTO dto : dtos) {
//            Link deviceLink = linkTo(methodOn(DeviceController.class)
//                    .getDevices(dto.getId())).withRel("deviceDetails");
//            dto.add(deviceLink);
//        }
       return dtos;
    }

    @PostMapping()
    //post
    public ResponseEntity<Long> insertProsumer(@Valid @RequestBody DeviceDetailsDTO deviceDTO) {
        System.out.println("IS AICI DA NU POT SCRIE!");
        Long deviceID = deviceService.insert(deviceDTO);
        return new ResponseEntity<>(deviceID, HttpStatus.CREATED);
    }
//get
    @GetMapping(value = "/response1/{id}")
    public ResponseEntity<DeviceDetailsDTO> getDevice(@PathVariable("id") Long deviceId) {
        DeviceDetailsDTO dto = deviceService.findDeviceById(deviceId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
///get
    @GetMapping(value = "/read/{id}")
    public ResponseEntity<DeviceDetailsDTO> getDeviceM(@PathVariable("id") Long deviceId) {
        DeviceDetailsDTO dto = deviceService.findDeviceById(deviceId);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //put
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Void> updateDevice(
            @PathVariable("id") Long deviceId,
            @Valid @RequestBody DeviceDetailsDTO deviceDTO
    ) {
        if (deviceService.updateDevice(deviceId, deviceDTO)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //delete
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Long deviceId) {
        if (deviceService.deleteDevice(deviceId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}