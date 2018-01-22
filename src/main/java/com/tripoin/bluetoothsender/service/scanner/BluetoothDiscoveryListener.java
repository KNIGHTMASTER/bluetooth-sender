package com.tripoin.bluetoothsender.service.scanner;

import javax.bluetooth.*;
import javax.microedition.io.Connector;
import javax.obex.ClientSession;
import javax.obex.HeaderSet;
import javax.obex.Operation;
import javax.obex.ResponseCodes;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created on 12/19/17.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class BluetoothDiscoveryListener implements DiscoveryListener {

    private Object lock;
    private List<RemoteDevice> remoteDevices;

    public BluetoothDiscoveryListener(Object lock, List<RemoteDevice> remoteDevices) {
        this.lock = lock;
        this.remoteDevices = remoteDevices;
    }

    @Override
    public void deviceDiscovered(RemoteDevice remoteDevice, DeviceClass deviceClass) {
        String name;
        try {
            name = remoteDevice.getFriendlyName(true);
        } catch (Exception e) {
            name = remoteDevice.getBluetoothAddress();
        }
        remoteDevices.add(remoteDevice);
        System.out.println("device found: " + name);
    }

    @Override
    public void servicesDiscovered(int transId, ServiceRecord[] serviceRecords) {
        for (ServiceRecord serviceRecord : serviceRecords) {
            String url = serviceRecord.getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            if (url == null) {
                continue;
            }
            DataElement serviceName = serviceRecord.getAttributeValue(0x0100);
            if (serviceName != null) {
                System.out.println("service " + serviceName.getValue() + " found " + url);

                if (serviceName.getValue().equals("OBEX Object Push")) {
                    sendMessageToDevice(url);
                }
            } else {
                System.out.println("service found " + url);
            }
        }
    }

    @Override
    public void serviceSearchCompleted(int i, int i1) {

    }

    @Override
    public void inquiryCompleted(int i) {
        synchronized(lock){
            lock.notify();
        }
    }

    private static void sendMessageToDevice(String serverURL){
        ClientSession clientSession = null;
        try{
            System.out.println("Connecting to " + serverURL);

            clientSession = (ClientSession) Connector.open(serverURL);
            HeaderSet hsConnectReply = clientSession.connect(null);
            System.out.println("1");
            if (hsConnectReply.getResponseCode() != ResponseCodes.OBEX_HTTP_OK) {
                System.out.println("Failed to connect");
                return;
            }

            HeaderSet hsOperation = clientSession.createHeaderSet();
            hsOperation.setHeader(HeaderSet.NAME, "Hello.txt");
            hsOperation.setHeader(HeaderSet.TYPE, "text");

            System.out.println("2");
            //Create PUT Operation
            Operation putOperation = clientSession.put(hsOperation);

            System.out.println("3");
            // Send some text to server
            byte data[] = "Hello World !!!".getBytes("iso-8859-1");
            OutputStream os = putOperation.openOutputStream();
            os.write(data);
            os.close();
            System.out.println("4");
            putOperation.close();

            clientSession.disconnect(null);

            clientSession.close();
            System.out.println("5");
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (clientSession != null) {
                try {
                    clientSession.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
