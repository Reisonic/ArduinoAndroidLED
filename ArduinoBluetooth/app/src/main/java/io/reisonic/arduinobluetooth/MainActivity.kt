package io.reisonic.arduinobluetooth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

/**
 * <h1>Control LED sensor using bluetooth</h1>
 * This program implements an application that
 * control LED and RGB colors on Arduino UNO
 *
 * @author  Kosmachev Vladislav
 * @version 1.0
 * @since   2021-01-22
 */
class MainActivity : AppCompatActivity() {

    private val presenter : MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.requestFeature(Window.FEATURE_NO_TITLE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.init(this)

        red_led.setOnClickListener {
            presenter.status_red = presenter.LED_On_Off("1","2", presenter.status_red, red_led)
        }

        green_led.setOnClickListener {
            presenter.status_green = presenter.LED_On_Off("5","6", presenter.status_green, green_led)
        }

        blue_led.setOnClickListener {
            presenter.status_blue = presenter.LED_On_Off("3","4", presenter.status_blue, blue_led)
        }

        rgb.setOnClickListener {
            presenter.RGB_On_Off(this)
        }

        connect.setOnClickListener {
            presenter.connect(this)
        }

        disconnect.setOnClickListener {
            presenter.disconnect(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.offlineThreads()
    }

}