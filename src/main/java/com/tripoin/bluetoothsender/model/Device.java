package com.tripoin.bluetoothsender.model;

import javax.persistence.*;

/**
 * Created on 12/20/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Entity
@Table(name = "mst_device")
public class Device {


    private Long id;
    private String macAddress;
    private String name;
    private Integer connectionStatus;
    private Integer trustStatus;
    private Integer pairStatus;
    private Integer blockStatus;
    private Integer legacyPairingStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "mac_address")
    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "connection_status")
    public Integer getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(Integer connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    @Column(name = "trust_status")
    public Integer getTrustStatus() {
        return trustStatus;
    }

    public void setTrustStatus(Integer trustStatus) {
        this.trustStatus = trustStatus;
    }

    @Column(name = "pair_status")
    public Integer getPairStatus() {
        return pairStatus;
    }

    public void setPairStatus(Integer pairStatus) {
        this.pairStatus = pairStatus;
    }

    @Column(name = "block_status")
    public Integer getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(Integer blockStatus) {
        this.blockStatus = blockStatus;
    }

    @Column(name = "legacy_pairing_status")
    public Integer getLegacyPairingStatus() {
        return legacyPairingStatus;
    }

    public void setLegacyPairingStatus(Integer legacyPairingStatus) {
        this.legacyPairingStatus = legacyPairingStatus;
    }
}
