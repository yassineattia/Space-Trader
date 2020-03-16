package edu.gatech.cs2340.spacetrader.viewmodel

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import edu.gatech.cs2340.spacetrader.entity.GameDifficulty
import edu.gatech.cs2340.spacetrader.entity.ShipType
import edu.gatech.cs2340.spacetrader.entity.SkillsData
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.model.Player
import edu.gatech.cs2340.spacetrader.util.DataType
import edu.gatech.cs2340.spacetrader.util.Size
import edu.gatech.cs2340.spacetrader.views.interfaces.ConfigViewModelProvider
import kotlinx.android.synthetic.main.activity_configuration.*

class ConfigurationViewModel(private val view: AppCompatActivity) {

    private var player: Player? = null
    private var diff: GameDifficulty? = null
    private val vmProviders = ArrayList<ConfigViewModelProvider>()

    fun handleFragmentAttach(fragment: Fragment?) {
        if(fragment is ConfigViewModelProvider) {
            vmProviders.add(fragment)
        }
    }

    fun handleStartGameClick(): Boolean {
        val toastText: String? = onDataSubmission()

        if (toastText != null) {
            Toast.makeText(view.applicationContext, toastText, Toast.LENGTH_SHORT).show()
            return false
        }

        startGame()
        return true
    }

    private fun onDataSubmission(): String? {
        val dataMap = mutableMapOf<DataType, Any>()

        for (provider in vmProviders) {
            val vm = provider.provideVM()

            if(vm is ValidatableConfigViewModel) {
                val valid = vm.validate()

                if(!valid) {
                    return vm.getInvalidMessage()
                }
            }

            dataMap[vm.getDataType()] = vm.processData()
        }

        val name = dataMap[DataType.NAME]!! as String
        val skills = dataMap[DataType.SKILLS] as SkillsData
        val diff = dataMap[DataType.DIFFICULTY] as GameDifficulty

        this.diff = diff
        createPlayer(name, skills)
        return null
    }

    private fun startGame() {
        val gameMan = GameManager(player!!, diff!!)
        val layout = view.configScreen
        val width = layout.width
        val height = layout.height

        GameManager.SIZE = Size(width, height)
        GameManager.INSTANCE = gameMan
    }

    //creates a player and logs the data to the logcat if player is valid
    private fun createPlayer(name: String, skill: SkillsData) {
        player = Player(name, skill, ShipType.GNAT)
        Log.d("SSVM", player.toString())
    } //createPlayer
} //ConfigurationViewModel