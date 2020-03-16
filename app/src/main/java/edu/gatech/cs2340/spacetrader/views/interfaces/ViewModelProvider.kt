package edu.gatech.cs2340.spacetrader.views.interfaces

import edu.gatech.cs2340.spacetrader.viewmodel.IViewModel

interface ViewModelProvider {
    fun provideVM(): IViewModel
}