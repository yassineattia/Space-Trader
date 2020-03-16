package edu.gatech.cs2340.spacetrader.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.viewmodel.MainMenuViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainMenuActivity : AppCompatActivity() {
    private val viewModel = MainMenuViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = "Main Menu"
        setSupportActionBar(toolbar)

        newGame.setOnClickListener {
            val intent = Intent(this, ConfigurationActivity::class.java)
            startActivity(intent)
        }//newGame click listener

        loadGameButton.setOnClickListener{
            viewModel.load()
        } //loadGameButton click listener
    } //onCreate
} //MainMenuActivity
