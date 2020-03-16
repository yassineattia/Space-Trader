package edu.gatech.cs2340.spacetrader.views.configfragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.entity.GameDifficulty
import edu.gatech.cs2340.spacetrader.viewmodel.ConfigViewModel
import edu.gatech.cs2340.spacetrader.viewmodel.GameDifficultyViewModel
import edu.gatech.cs2340.spacetrader.views.interfaces.ConfigViewModelProvider
import kotlinx.android.synthetic.main.fragment_game_difficulty.*

class GameDifficultyFragment : Fragment(), ConfigViewModelProvider,
        AdapterView.OnItemSelectedListener{
    private val difficultyVM = GameDifficultyViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_game_difficulty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ArrayAdapter<GameDifficulty>(activity, android.R.layout.simple_spinner_item,
                GameDifficulty.values())
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        gameDifficultySpinner!!.adapter = adapter

        gameDifficultySpinner!!.onItemSelectedListener = this
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        difficultyVM.setDifficulty(GameDifficulty.values()[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) { }

    override fun provideVM(): ConfigViewModel {
        return difficultyVM
    }
}
