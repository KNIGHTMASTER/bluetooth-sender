package com.tripoin.bluetoothsender.service.scanner;

import com.tripoin.bluetoothsender.model.DeviceDTO;

import java.util.List;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public interface IScannerService {

    List<DeviceDTO> scan();

}
