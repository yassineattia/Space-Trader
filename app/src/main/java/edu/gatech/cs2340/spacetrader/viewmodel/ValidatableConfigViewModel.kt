package edu.gatech.cs2340.spacetrader.viewmodel

interface ValidatableConfigViewModel: ConfigViewModel {
    fun validate(): Boolean

    fun getInvalidMessage(): String
}