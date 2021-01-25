package io.reisonic.arduinobluetooth.Model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import io.reisonic.arduinobluetooth.View.MainActivity
import io.reisonic.arduinobluetooth.R
import io.reisonic.arduinobluetooth.View.DeviceActivity

/**
 * Helper class for RecyclerView
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-22
 */
class DeviceList(var context: Context, var device:List<Device>):RecyclerView.Adapter<DeviceList.DeviceViewHolder>() {

    class DeviceViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cv: CardView = itemView.findViewById<View>(R.id.cv) as CardView
        var btn: Button = itemView.findViewById<View>(R.id.mac_address_btn) as Button

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.devices, parent, false)
        return DeviceViewHolder(v)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.btn.text = device[position].name + " " + device[position].address
        holder.btn.setOnClickListener {
            DeviceActivity.mac_address = device[position].address
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun getItemCount(): Int {
        return device.size
    }
}