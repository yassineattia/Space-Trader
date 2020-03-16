package edu.gatech.cs2340.spacetrader.views.configfragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.viewmodel.ConfigViewModel
import edu.gatech.cs2340.spacetrader.viewmodel.SkillSelectionViewModel
import edu.gatech.cs2340.spacetrader.views.interfaces.ConfigViewModelProvider

class SkillFragment : Fragment(), ConfigViewModelProvider {
    private val skillVm = SkillSelectionViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skill_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        skillVm.handleViewCreation(view, this)
    }

    override fun provideVM(): ConfigViewModel {
        return skillVm
    }
}