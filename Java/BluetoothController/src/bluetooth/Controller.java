package bluetooth;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

import javax.bluetooth.*;
import java.io.IOException;


public class Controller {

    @FXML
    Button connect;
    @FXML
    Button slowFuck, blowJob, justTheTip, roughSex;
    @FXML
    Slider strokeSpeed, pulseSpeed, minStroke, maxStroke;


    //private static final  UUID uuid = UUID.fromString("88f80580-0000-01e6-aace-0002a5d5c51b");

    //Start Methods


    UUID uuid = new UUID("88f80580000001e6aace0002a5d5c51b", false);



    public static Object lock = new Object();



    @FXML
    private void connectBluetooth() throws IOException {
        /*

        LocalDevice localDevice = LocalDevice.getLocalDevice();

        DiscoveryAgent agent = localDevice.getDiscoveryAgent();

        agent.startInquiry(DiscoveryAgent.GIAC, new MyDiscoveryListener());

        try {
            synchronized(lock){
                lock.wait();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Device Inquiry Completed. ");

        */

        System.out.println(LocalDevice.getLocalDevice().getDiscoveryAgent().selectService(uuid, 0, false));

        RemoteDevice[] rdList = LocalDevice.getLocalDevice().getDiscoveryAgent().retrieveDevices(DiscoveryAgent.PREKNOWN);

        for(int i = 0; i < rdList.length; i++ )
        System.out.println(rdList[i].getFriendlyName(true));


/*
        try {
            name = btDevice.getFriendlyName(false);
        } catch (Exception e) {
            name = btDevice.getBluetoothAddress();
        }
*/

    }

/*
        RemoteDevice[] remoteDevice = localDevice.getDiscoveryAgent().retrieveDevices(DiscoveryAgent.PREKNOWN);

        for(RemoteDevice d : remoteDevice){
            System.out.println("Device Name: " + d.getFriendlyName(false));
            System.out.println("Bluetooth Address" + d.getBluetoothAddress() + "\n");
        }
*/





    }





