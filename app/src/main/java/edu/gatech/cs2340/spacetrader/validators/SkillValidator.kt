package edu.gatech.cs2340.spacetrader.validators

import edu.gatech.cs2340.spacetrader.util.SkilledData

class SkillValidator(private val data: SkilledData) : Validator {

    override fun validate(): Boolean {
        return data.isFullyAllocated()
    }
}