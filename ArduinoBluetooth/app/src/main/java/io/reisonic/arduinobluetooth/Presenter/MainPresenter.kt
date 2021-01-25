package io.reisonic.arduinobluetooth.Presenter

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reisonic.arduinobluetooth.Presenter.Interfaces.MainInterface
import io.reisonic.arduinobluetooth.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

/**
 * Сlass for explaining interface functions
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-22
 */
class MainPresenter(): MainInterface {

    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothSocket: BluetoothSocket? = null

    private var status_connection:Boolean = false // Message sending status

    private var message:String = "0" // Main message for sending on board

    // Check-mate on/off LED colors on board
    var status_red:Boolean = false
    var status_blue:Boolean = false
    var status_green:Boolean = false

    private var status_rgb:Boolean = false // Check-mate on/off RGB LED color on board

    private val uuid:UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB") // Random UUID for Android device

    override fun init(activity: AppCompatActivity) {
        activity.disconnect.isEnabled = false

        activity.red_led.setText(R.string.off)
        activity.green_led.setText(R.string.off)
        activity.blue_led.setText(R.string.off)
        activity.rgb.setText(R.string.off)

        status_connection = false
    }

    override fun connectBluetoothDevice(activity: AppCompatActivity, mac_address: String) {
        try {
            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() // get the mobile bluetooth device
            val device: BluetoothDevice = bluetoothAdapter?.getRemoteDevice(mac_address)!! // connects to the device's address and checks if it's available
            bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(uuid) // create a RFCOMM (SPP) connection
            BluetoothAdapter.getDefaultAdapter().cancelDiscovery()
            bluetoothSocket?.connect() //start connection

            activity.runOnUiThread {
                activity.contact.setBackgroundResource(android.R.color.holo_green_light)
                activity.disconnect.isEnabled = true
                activity.disconnect.setBackgroundResource(R.drawable.active)
            }

            status_connection = true
        } catch (e: Exception){
            e.printStackTrace()
        }

    }

    override fun colorHex(color: Int): String {
        val a: Int = Color.alpha(color) // alpha channel
        val r: Int = Color.red(color) // red channel
        val g: Int = Color.green(color) // green channel
        val b: Int = Color.blue(color) // blue channel

        return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b)
    }

    override fun LED_On_Off(on_value:String, off_value:String, _trigger:Boolean,textView: TextView):Boolean {
        var trigger = _trigger

        if (trigger == false){
            textView.text = "On"
            message = on_value
            trigger = true
        } else {
            trigger = false
            message = off_value
            textView.text = "Off"
        }

        return trigger
    }

    override fun disconnect(activity: AppCompatActivity){
        bluetoothSocket?.close()

        activity.connect.isEnabled = true
        activity.disconnect.isEnabled = false

        activity.disconnect.setBackgroundResource(R.drawable.offline)
        activity.contact.setBackgroundResource(android.R.color.holo_red_light)
        activity.connect.setBackgroundResource(R.drawable.active)

        status_connection = false
    }

    override fun connect(activity: AppCompatActivity, mac_address:String) {

        activity.connect.isEnabled = false
        activity.connect.setBackgroundResource(R.drawable.offline)

        GlobalScope.launch {
            while (status_connection == false){
                connectBluetoothDevice(activity,mac_address)
            }

            while (status_connection == true){
                if (bluetoothSocket != null){
                    try {
                        bluetoothSocket?.outputStream?.write(message.toByteArray())
                        Log.i("Message", message)
                    } catch (e: Exception){
                        e.printStackTrace()
                    }
                }
                delay(100)
            }
        }
    }

    override fun RGB_On_Off(activity: AppCompatActivity) {
        if (status_rgb == false){

            activity.rgb.setText(R.string.on)
            status_rgb = true

            GlobalScope.launch {
                while (status_rgb == true){
                    val num:Int = activity.color.color
                    val hex_color = colorHex(num)
                    val hex = hex_color.split("0xFF")

                    message = hex[1]

                    delay(250)
                }
            }

        } else {
            activity.rgb.setText(R.string.off)
            status_rgb = false
        }
    }

    override fun offlineThreads() {
        status_connection = true
        status_rgb = false
    }
}