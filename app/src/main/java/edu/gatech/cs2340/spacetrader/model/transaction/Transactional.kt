package edu.gatech.cs2340.spacetrader.model.transaction

import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.entity.Good
import edu.gatech.cs2340.spacetrader.entity.inventory.Inventory

interface Transactional {

    fun performTransaction(good: Good, amount: Int, activity: AppCompatActivity, inventory: Inventory)

    fun mode(): String
}