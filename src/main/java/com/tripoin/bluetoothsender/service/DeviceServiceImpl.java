package com.tripoin.bluetoothsender.service;

import com.tripoin.bluetoothsender.dao.IDeviceDAO;
import com.tripoin.bluetoothsender.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class DeviceServiceImpl implements IDeviceService {

    @Autowired
    private IDeviceDAO deviceDAO;

    @Override
    public List<Device> findAll() {
        return deviceDAO.findAll();
    }

    @Override
    public Device findByMacAddress(String p_MacAddress) {
        return deviceDAO.findByMacAddress(p_MacAddress);
    }

    @Override
    public void insert(Device p_Device) {
        deviceDAO.save(p_Device);
    }

    @Override
    public void update(Device p_Device) {
        deviceDAO.save(p_Device);
    }
}
