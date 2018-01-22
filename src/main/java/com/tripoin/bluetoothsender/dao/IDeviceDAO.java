package com.tripoin.bluetoothsender.dao;

import com.tripoin.bluetoothsender.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IDeviceDAO extends JpaRepository<Device, Long> {

    Device findByMacAddress(String p_MacAddress);
}
