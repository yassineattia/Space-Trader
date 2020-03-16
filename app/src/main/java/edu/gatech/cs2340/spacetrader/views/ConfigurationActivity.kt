package edu.gatech.cs2340.spacetrader.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.viewmodel.ConfigurationViewModel
import kotlinx.android.synthetic.main.activity_configuration.*

class ConfigurationActivity : AppCompatActivity() {
    private val configVM = ConfigurationViewModel(this)

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)

        configVM.handleFragmentAttach(fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuration)

        start_game.setOnClickListener {
            if (configVM.handleStartGameClick()) {
                startActivity(Intent(this, UniverseMapActivity::class.java))
            }
        } //setOnClickListener

        exit_game.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
        } //exitGame set on click
    } //onCreate
} //ConfigurationActivity
