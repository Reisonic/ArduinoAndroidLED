package io.reisonic.arduinobluetooth.Model

/**
 * Model data for Recycler View
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-22
 */
class Device(_mac_address:String,_name_device:String) {

    val address:String
    val name:String

    init {
        address=_mac_address
        name = _name_device
    }
}