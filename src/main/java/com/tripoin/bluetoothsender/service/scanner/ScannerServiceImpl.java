package com.tripoin.bluetoothsender.service.scanner;

import com.tripoin.bluetoothsender.model.DeviceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Service
public class ScannerServiceImpl implements IScannerService {

    private static final Object lock=new Object();

    public ArrayList<RemoteDevice> devices = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ScannerServiceImpl.class);

    @Override
    public List<DeviceDTO> scan() {
        List<DeviceDTO> result = new ArrayList<>();
        try{
            BluetoothDiscoveryListener bluetoothDiscoveryListener = new BluetoothDiscoveryListener(lock, devices);
            LocalDevice localDevice = LocalDevice.getLocalDevice();
            DiscoveryAgent agent = localDevice.getDiscoveryAgent();
            agent.startInquiry(DiscoveryAgent.GIAC, bluetoothDiscoveryListener);
            try {
                synchronized(lock){
                    lock.wait();
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("Device Inquiry Completed. ");
            LOGGER.info("Scanned Devices " + devices.size());
            for (RemoteDevice device : devices) {
                LOGGER.info("Device Address : " + device.getBluetoothAddress());
                DeviceDTO deviceDTO = new DeviceDTO();
                deviceDTO.setMacAddress(device.getBluetoothAddress());
                deviceDTO.setName(device.getFriendlyName(true));
                result.add(deviceDTO);
            }
        }
        catch (Exception e) {
            LOGGER.error(e.toString());
        }
        LOGGER.info("Scanning Finished");
        return result;
    }
}
