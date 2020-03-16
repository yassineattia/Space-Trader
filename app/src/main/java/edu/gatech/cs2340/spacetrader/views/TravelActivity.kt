package edu.gatech.cs2340.spacetrader.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.model.Planet
import edu.gatech.cs2340.spacetrader.viewmodel.TravelViewModel
import kotlinx.android.synthetic.main.activity_travel.*

class TravelActivity : AppCompatActivity() {
    private val viewModel = TravelViewModel( this )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel)
        travel_msg.text = getString(R.string.travel_msg, (intent.extras!!["Planet"] as Planet).name)

        viewModel.addExtras( intent.extras!!["Planet"] as Planet )

        travel_cancel.setOnClickListener {
            startActivity(Intent(this, UniverseMapActivity::class.java))
            finish()
        }
        travel_go.setOnClickListener {
            viewModel.attemptTravel(intent.extras!!["Planet"] as Planet)
        }
    }
}
