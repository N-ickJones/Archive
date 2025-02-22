package bluetooth;
/*
private Set<BluetoothDevice> pairedDevices;
public static ArrayList<Object> BondedDeviceList;
public static ArrayList<Object> NewDeviceList;

public void makeDiscoverable()
        {
        discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        activity.startActivity(discoverableIntent);
        }

//It will Add the paired device in the BondedDeviceList
public void queryPairedDevice(){
        pairedDevices = mBluetoothAdapter.getBondedDevices();

        // If there are paired devices
        if(pairedDevices==null)
        {
        //No Bonded Devices

        }else
        {
        if (pairedDevices.size() > 0) {
        // Loop through paired devices
        for (BluetoothDevice device : pairedDevices) {
        BondedDeviceList.add(device);
        }
        BondedDeviceList.add("End");
        }
        }
        }

//Broadcast Receiver will find the Available devices and the discovery finished
private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
@Override
public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND.equals(action.trim())) {
        // Get the BluetoothDevice object from the Intent
        BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
        // If it's already paired, skip it, because it's been listed already
        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
        NewDeviceList.add(device);
        }
        // When discovery is finished, change the Activity title
        } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
        if (NewDeviceList.isEmpty() == true) {
        String noDevices = "No Devices";
        NewDeviceList.add(noDevices);
        }
        System.out.println("Discovery Finished!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        NewDeviceList.add("End");
        }
        }
        };

//This is query for the bluetooth devices
public void queryDevices(){
        actionFoundFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        activity.registerReceiver(mReceiver, actionFoundFilter);
        // Don't forget to unregister during onDestroy

        discoveryFinishedFilter = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        activity.registerReceiver(mReceiver, discoveryFinishedFilter);
        // Don't forget to unregister during onDestroy
        queryPairedDevice();
        mBluetoothAdapter.startDiscovery();
        }


//Unregister the receivers
public void unregisterReceiver() {
        // Make sure we're not doing discovery anymore
        if (mBluetoothAdapter != null) {
        mBluetoothAdapter.cancelDiscovery();
        }
        // Unregister broadcast listeners
        activity.unregisterReceiver(mReceiver);


        }

        */