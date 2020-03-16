package edu.gatech.cs2340.spacetrader.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.GridLayout
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.viewmodel.UniverseMapViewModel

class UniverseMapActivity : AppCompatActivity() {
    private val viewModel = UniverseMapViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_universe_map)
        val gridLayout: GridLayout = findViewById(R.id.universe_gridlayout)
        val fuel = GameManager.INSTANCE!!.player.ship.getFuelLevel()

        viewModel.populateGridLayout(gridLayout, fuel)
    } //onCreate

} //UniverseMapActivity
