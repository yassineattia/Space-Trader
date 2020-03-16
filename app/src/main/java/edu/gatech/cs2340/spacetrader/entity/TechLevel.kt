package edu.gatech.cs2340.spacetrader.entity

import java.io.Serializable

enum class TechLevel: Comparable<TechLevel>, Serializable {
    PRE_AGRICULTURE, AGRICULTURE, MEDIEVAL, RENAISSANCE, EARLY_INDUSTRIAL, INDUSTRIAL,
    POST_INDUSTRIAL, HI_TECH
}