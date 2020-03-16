package edu.gatech.cs2340.spacetrader.util

import java.io.Serializable

data class Coordinate(val x: Int, val y: Int): Serializable {

    fun calculateDistance (coordinate: Coordinate): Double {
        return Math.sqrt(Math.pow((x - coordinate.x).toDouble(), 2.0)
                + Math.pow((y - coordinate.y).toDouble(), 2.0))
    }

    override fun toString(): String {
        return "{x=$x,y=$y}"
    }

    override fun equals(other: Any?): Boolean {
        if (other is Coordinate) {
            if(other.x == x && other.y == y) {
                return true
            }
        }

        return false
    }

    override fun hashCode(): Int {
        var result = 17
        result = result * 31 + x
        result = result * 31 + y
        return result
    }
}