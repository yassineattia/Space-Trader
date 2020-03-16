package edu.gatech.cs2340.spacetrader.entity

import edu.gatech.cs2340.spacetrader.model.Planet

enum class Good(val MTLP: TechLevel, val MTLU: TechLevel, val TTP: Int, private val basePrice: Int,
                private val IPL: Int, private val Var: Int, private val IE: IncreaseEvent,
                val CR: String, val ER: String, val MTL: Int, val MTH: Int) {
    Water(TechLevel.PRE_AGRICULTURE, TechLevel.PRE_AGRICULTURE, 2, 30, 3,
            4, IncreaseEvent.DROUGHT, "LOTSOFWATER", "DESERT", 30, 50),
    Furs(TechLevel.PRE_AGRICULTURE, TechLevel.PRE_AGRICULTURE, 0, 250, 10,
            10, IncreaseEvent.COLD, "RICHFAUNA", "LIFELESS", 230, 280),
    Food(TechLevel.AGRICULTURE, TechLevel.PRE_AGRICULTURE, 1, 100, 5,
            5, IncreaseEvent.CROPFAIL, "RICHSOIL", "POORSOIL", 90, 160),
    Ore(TechLevel.MEDIEVAL, TechLevel.MEDIEVAL, 3, 350, 20,
            10, IncreaseEvent.WAR, "MINERALRICH", "MINERALPOOR", 350, 420),
    Games(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE, 6, 250, -10,
            5, IncreaseEvent.BOREDOM, "ARTISTIC", "Never", 160, 270),
    Firearms(TechLevel.RENAISSANCE, TechLevel.AGRICULTURE, 5, 1250, -75,
            100, IncreaseEvent.WAR, "WARLIKE", "Never", 600, 1100),
    Medicine(TechLevel.EARLY_INDUSTRIAL, TechLevel.AGRICULTURE, 6, 650, -20,
            10, IncreaseEvent.PLAGUE, "LOTSOFHERBS", "Never", 400, 700),
    Machines(TechLevel.EARLY_INDUSTRIAL, TechLevel.RENAISSANCE, 5, 900, -30,
            5, IncreaseEvent.LACKOFWORKERS, "Never", "Never", 600, 800),
    Narcotics(TechLevel.INDUSTRIAL, TechLevel.PRE_AGRICULTURE, 5, 3500, -125,
            150, IncreaseEvent.BOREDOM, "WEIRDMUSHROOMS", "Never", 2000, 3000),
    Robots (TechLevel.POST_INDUSTRIAL, TechLevel.EARLY_INDUSTRIAL, 7, 5000, -150,
            100, IncreaseEvent.LACKOFWORKERS, "Never", "Never", 3500, 3500),
    ;

    fun price(p: Planet): Int {
        var price = basePrice +
                (IPL * (p.techLevel.ordinal - MTLP.ordinal) +
                (basePrice * ((0..Var).random()/100)))

        if ( p.event != IncreaseEvent.NONE && IE == p.event ) {
            price *= 1000
        }

        return price
    }
}