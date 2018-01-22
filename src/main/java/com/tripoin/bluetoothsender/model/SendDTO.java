package com.tripoin.bluetoothsender.model;

import java.io.Serializable;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class SendDTO implements Serializable {
    /**
     *
     *
     */
    private static final long serialVersionUID = 8006280663819118860L;

    private String filePath;
    private String macAddress;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Override
    public String toString() {
        return "SendDTO{" +
                "filePath='" + filePath + '\'' +
                ", macAddress='" + macAddress + '\'' +
                '}';
    }
}
