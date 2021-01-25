package io.reisonic.arduinobluetooth.View

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import io.reisonic.arduinobluetooth.Model.DeviceList
import io.reisonic.arduinobluetooth.Presenter.DevicePresenter
import io.reisonic.arduinobluetooth.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 * Class for detecting paired bluetooth devices
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-22
 */
open class DeviceActivity:AppCompatActivity() {

    private val presenter: DevicePresenter by inject()

    companion object{
        lateinit var mac_address:String
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitivity_device)
        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        GlobalScope.launch {
            val bluetoothAdapter:BluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val pairedDevices: Set<BluetoothDevice> = bluetoothAdapter.bondedDevices
            for (device in pairedDevices) {
                val deviceName = device.name
                val deviceHardwareAddress = device.address // MAC address
                Log.i("Devices", deviceHardwareAddress)
                presenter.initData(deviceHardwareAddress, deviceName)
            }
            presenter.initAdapter(recyclerView,this@DeviceActivity, this@DeviceActivity)
        }
    }
}