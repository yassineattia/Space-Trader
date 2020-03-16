package edu.gatech.cs2340.spacetrader.views.interfaces

import edu.gatech.cs2340.spacetrader.viewmodel.ConfigViewModel

interface ConfigViewModelProvider : ViewModelProvider {
    override fun provideVM(): ConfigViewModel
}