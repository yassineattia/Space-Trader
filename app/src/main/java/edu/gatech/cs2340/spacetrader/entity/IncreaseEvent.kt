package edu.gatech.cs2340.spacetrader.entity

enum class IncreaseEvent(private val nameString: String) {
    NONE(""),
    DROUGHT("a drought"),
    COLD("extreme cold"),
    CROPFAIL("failed crops"),
    WAR("a war"),
    BOREDOM("boredom"),
    PLAGUE("a plague"),
    LACKOFWORKERS("a lack of workers");

    override fun toString(): String {
        return nameString
    }
}