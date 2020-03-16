package edu.gatech.cs2340.spacetrader.viewmodel

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.TextView
import android.widget.Toast
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.entity.IncreaseEvent
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.model.Planet
import edu.gatech.cs2340.spacetrader.model.saving.Savable
import edu.gatech.cs2340.spacetrader.model.saving.SerialSave
import kotlinx.android.synthetic.main.activity_travel.*

class PlanetMenuViewModel(private val view: AppCompatActivity) {
    fun save() {
        val saver: Savable = SerialSave()
        val result = if(saver.save(GameManager.INSTANCE!!, GameManager.SIZE,
                        view.applicationContext)) {
            "Save Successful"
        } else {
            "Save failed, try again later"
        }
        Toast.makeText(view, result, Toast.LENGTH_SHORT).show()
    } //saveButton listener

    fun addExtras(targetPlanet: Planet) {
        if ( targetPlanet.event != IncreaseEvent.NONE ) {
            view.event_msg.text = "WARNING! This planet is currently experiencing " +
                    targetPlanet.event + "!"
        }
    }

    fun showStatus() {
        val inflater = view.layoutInflater
        val statusView = inflater.inflate(R.layout.popup_status, null)

        val popUpWindow = PopupWindow( statusView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        if ( Build.VERSION.SDK_INT >= 21 ){
            popUpWindow.elevation = 5.0f
        }

        val closeButton = statusView.findViewById<ImageButton>( R.id.close_button )

        closeButton.setOnClickListener {
            popUpWindow.dismiss()
        }

        val planetText = statusView.findViewById<TextView>( R.id.current_planet )
        val fuelText = statusView.findViewById<TextView>( R.id.current_fuel )
        val coinsText = statusView.findViewById<TextView>( R.id.current_coins )
        val instance = GameManager.INSTANCE!!

        planetText.text = "Planet: " + instance.currentPlanet.name
        fuelText.text = "Fuel: " + instance.player.ship.fuel
        coinsText.text = "Coins: " + instance.player.credits

        popUpWindow.showAtLocation( closeButton, Gravity.CENTER, 0, 0 )
    }
} //PlanetMenuViewModel