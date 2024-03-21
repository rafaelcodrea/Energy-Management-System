package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DeviceDTO;
import ro.tuc.ds2020.dtos.DeviceDetailsDTO;
import ro.tuc.ds2020.dtos.builders.DeviceBuilder;
import ro.tuc.ds2020.entities.Device;
import ro.tuc.ds2020.repositories.DeviceRepository;

import java.util.List;
import java.util.Optional;
//import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceService.class);
    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<DeviceDTO> findDevices() {
        List<Device> deviceList = deviceRepository.findAll();
        return deviceList.stream()
                .map(DeviceBuilder::toDeviceDTO)
                .collect(Collectors.toList());
    }
    ///////
    public DeviceDetailsDTO findDeviceById(Long id) {
        Optional<Device> prosumerOptional = deviceRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Device.class.getSimpleName() + " with id: " + id);
        }
        return DeviceBuilder.toDeviceDetailsDTO(prosumerOptional.get());
    }

    public List<Device> findDevicesForUser(Long id) {
        List<Device> prosumerOptional = deviceRepository.findDevicesForUser(id);
        return prosumerOptional;
    }

    public Long insert(DeviceDetailsDTO deviceDTO) {
        Device device = DeviceBuilder.toEntity(deviceDTO);
        device = deviceRepository.save(device);
        LOGGER.debug("Person with id {} was inserted in db", device.getId());
        return device.getId();
    }
    public boolean updateDevice(Long deviceId, DeviceDetailsDTO updatedDeviceDTO) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);

        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            device.setAddress(updatedDeviceDTO.getAddress());
            device.setDescription(updatedDeviceDTO.getDescription());
            device.setMaxEnergyH(updatedDeviceDTO.getMaxEnergyH());
            device.setUser(updatedDeviceDTO.getUser());

            // Save the updated person entity
            deviceRepository.save(device);

            LOGGER.debug("Device with id {} was updated in the database", device.getId());
            return true; // Person updated successfully
        } else {
            LOGGER.error("Device with id {} was not found in the database", deviceId);
            return false; // Person not found
        }
    }
    public boolean deleteDevice(Long deviceId) {
        Optional<Device> deviceOptional = deviceRepository.findById(deviceId);

        if (deviceOptional.isPresent()) {
            Device device = deviceOptional.get();
            deviceRepository.delete(device);

            LOGGER.debug("Device with id {} was deleted from the database", device.getId());
            return true; // Person deleted successfully
        } else {
            LOGGER.error("Device with id {} was not found in the database", deviceId);
            return false; // Person not found
        }
    }


}
