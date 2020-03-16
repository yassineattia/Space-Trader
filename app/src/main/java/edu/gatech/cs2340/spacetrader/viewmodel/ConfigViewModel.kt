package edu.gatech.cs2340.spacetrader.viewmodel

import edu.gatech.cs2340.spacetrader.util.DataType

interface ConfigViewModel : IViewModel {
    fun processData(): Any

    fun getDataType(): DataType
} //GameSetup