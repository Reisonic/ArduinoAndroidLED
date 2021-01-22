package io.reisonic.arduinobluetooth

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * Functions used in application
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-22
 */
interface MainInterface {

    /**
     * Initialization of initial parameters current activity
     * @param activity - Current activity
     */
    fun init(activity: AppCompatActivity)

    /**
     * Connecting to current bluetooth device
     * @param activity - Current activity
     */
    fun connectBluetoothDevice(activity: AppCompatActivity)

    /**
     * Disconnecting current paired devices
     * @param activity - current activity
     */
    fun disconnect(activity: AppCompatActivity)

    /**
     * Connecting current paired devices
     * @param activity - current activity
     */
    fun connect(activity: AppCompatActivity)

    /**
     * Parser for color in hex value
     * @param color - ARGB color (format color 0x00000000)
     * @return String - RGB value in String type
     */
    fun colorHex(color: Int): String

    /**
     * On/Off LED
     * @param on_value - Value for activate LED signal
     * @param off_value - Value for deactivate LED signal
     * @param _trigger - Current value true or false
     * @param textView - textView for output value on display (On/Off)
     * @return Boolean - true/false (on/off) LED function
     */
    fun LED_On_Off(on_value:String, off_value:String, _trigger:Boolean,textView: TextView):Boolean

    /**
     * On/Off RGB
     * @param activity - Current activity
     */
    fun RGB_On_Off(activity: AppCompatActivity)

    /**
     * Finish all active threads in application
     */
    fun offlineThreads()
}