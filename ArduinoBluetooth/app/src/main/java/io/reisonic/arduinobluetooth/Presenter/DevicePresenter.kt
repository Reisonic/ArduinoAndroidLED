package io.reisonic.arduinobluetooth.Presenter

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reisonic.arduinobluetooth.Model.DeviceList
import io.reisonic.arduinobluetooth.Model.Device
import io.reisonic.arduinobluetooth.Presenter.Interfaces.DeviceInterface

/**
 * Сlass for explaining interface functions
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-22
 */
class DevicePresenter(): DeviceInterface {

    var devices:List<Device> = ArrayList()

    override fun initAdapter(recyclerView: RecyclerView, context: Context,activity: AppCompatActivity) {
        val adapter = DeviceList(context,devices)
        activity.runOnUiThread {
            recyclerView.adapter = adapter
            val llm = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = llm
            recyclerView.setHasFixedSize(true)
        }
    }

    override fun initData(mac_address:String, name:String) {
        (devices as ArrayList<Device>).add(Device(mac_address,name))
    }
}