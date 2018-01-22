package com.tripoin.bluetoothsender.model;

import java.io.Serializable;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DeviceDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = -7631052798659804145L;

    private String macAddress;
    private String name;

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
