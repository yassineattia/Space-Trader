package edu.gatech.cs2340.spacetrader.viewmodel.market

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import edu.gatech.cs2340.spacetrader.entity.Good
import edu.gatech.cs2340.spacetrader.entity.inventory.Inventory
import edu.gatech.cs2340.spacetrader.model.transaction.Transactional

class GoodQuantityPickerViewModel(private val dialog: Dialog,
                                  private val activity: AppCompatActivity){
    fun confirmTransaction(good: Good, quantity: Int, transactional: Transactional, notPlayerInventory: Inventory) {
        transactional.performTransaction(good, quantity, activity, notPlayerInventory)
        dialog.dismiss()
    } //confirmTransaction
} //GoodQuantityPickerViewModel