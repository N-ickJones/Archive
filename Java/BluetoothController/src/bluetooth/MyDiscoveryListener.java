package bluetooth;

import javax.bluetooth.*;

import static bluetooth.Controller.lock;

public class MyDiscoveryListener implements DiscoveryListener {


    @Override
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass arg1) {

        System.out.println("made it1");

        String name;
        try {
            name = btDevice.getFriendlyName(false);
        } catch (Exception e) {
            name = btDevice.getBluetoothAddress();
        }

        System.out.println("device found: " + name);

    }

    @Override
    public void inquiryCompleted(int arg0) {

        System.out.println("made it2");
        synchronized(lock){
            lock.notify();
        }
    }

    @Override
    public void serviceSearchCompleted(int arg0, int arg1) {

        System.out.println("made it3");
        synchronized (lock) {
            lock.notify();
        }
    }

    @Override
    public void servicesDiscovered(int arg0, ServiceRecord[] services) {

        System.out.println("made it4");
        for (int i = 0; i < services.length; i++) {
            String url = services[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
            if (url == null) {
                continue;
            }

            DataElement serviceName = services[i].getAttributeValue(0x0100);
            if (serviceName != null) {
                System.out.println("service " + serviceName.getValue() + " found " + url);
            } else {
                System.out.println("service found " + url);
            }

            if(serviceName.getValue().equals("OBEX Object Push")){
                //sendMessageToDevice(url);
            }
        }

    }

}
