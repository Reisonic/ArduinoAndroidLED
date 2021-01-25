package io.reisonic.arduinobluetooth.Presenter.Interfaces

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

/**
 * Functions used at start application
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-25
 */
interface DeviceInterface {

    /**
     * Initialization adapter in Recycler View
     * @param recyclerView - using recyclerView in application
     * @param activity - current activity in application
     * @param context - current context in application
     */
    fun initAdapter(recyclerView: RecyclerView,context: Context, activity: AppCompatActivity)


    /**
     * Initialization received data for Recycler View
     * @param mac_address - Mac address bluetooth devices
     * @param name - Name bluetooth devices
     */
    fun initData(mac_address:String, name:String)
}