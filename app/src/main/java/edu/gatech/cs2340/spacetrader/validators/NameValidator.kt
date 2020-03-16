package edu.gatech.cs2340.spacetrader.validators

import edu.gatech.cs2340.spacetrader.util.NamedData

class NameValidator(private val data: NamedData) : Validator {

    override fun validate(): Boolean {
        return data.isNotEmpty()
    }
}