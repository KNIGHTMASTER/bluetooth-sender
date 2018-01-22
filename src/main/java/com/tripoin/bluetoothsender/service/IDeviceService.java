package com.tripoin.bluetoothsender.service;

import com.tripoin.bluetoothsender.model.Device;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IDeviceService {

    List<Device> findAll();

    Device findByMacAddress(String p_MacAddress);

    @Transactional
    void insert(Device p_Device);

    @Transactional
    @Modifying
    void update(Device p_Device);
}
