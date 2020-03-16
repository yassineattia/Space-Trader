package edu.gatech.cs2340.spacetrader.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.viewmodel.PlanetMenuViewModel
import edu.gatech.cs2340.spacetrader.views.market.MarketMenuActivity
import kotlinx.android.synthetic.main.activity_planet_menu.*

class PlanetMenuActivity: AppCompatActivity() {
    private val viewModel = PlanetMenuViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planet_menu)

        welcome.text = "Welcome to ${GameManager.INSTANCE!!.currentPlanet.name }!" +
                " What would you like to do?"

        viewModel.addExtras( GameManager.INSTANCE!!.currentPlanet )

        market_button.setOnClickListener{
                startActivity(Intent(this, MarketMenuActivity::class.java).apply {
                    putExtra("merchant", false) })
        } //marketButton listener

        save_button.setOnClickListener {
            viewModel.save()
        } //saveButton click listener

        status_button.setOnClickListener {
            viewModel.showStatus()
        } //status_button click listener
    } //onCreate
} //PlanetMenuActivity