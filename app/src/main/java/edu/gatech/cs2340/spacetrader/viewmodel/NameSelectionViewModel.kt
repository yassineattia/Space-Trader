package edu.gatech.cs2340.spacetrader.viewmodel

import edu.gatech.cs2340.spacetrader.util.DataType
import edu.gatech.cs2340.spacetrader.util.NamedData
import edu.gatech.cs2340.spacetrader.validators.NameValidator

class NameSelectionViewModel: NamedData, ValidatableConfigViewModel {
    private var name: String
    private val validator: NameValidator

    init {
        name = ""
        validator = NameValidator(this)
    }

    fun setName(name: String) {
        this.name = name
    }

    override fun getName(): String {
        return name
    }

    override fun isNotEmpty(): Boolean {
        return getName().isNotEmpty()
    }

    override fun validate(): Boolean {
        return validator.validate()
    }

    override fun getInvalidMessage(): String {
        return "The name can't be empty!"
    }

    override fun processData(): Any {
        return name
    }

    override fun getDataType(): DataType {
        return DataType.NAME
    }
}