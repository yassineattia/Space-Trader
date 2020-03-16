package edu.gatech.cs2340.spacetrader.viewmodel.market

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import edu.gatech.cs2340.spacetrader.entity.Good
import edu.gatech.cs2340.spacetrader.entity.inventory.Inventory
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.model.transaction.TransactionMode
import edu.gatech.cs2340.spacetrader.views.market.GoodQuantityPickerDialog

class MarketViewModel(private val activity: AppCompatActivity) {
    fun populateMarketList(linearLayout: LinearLayout, mode: TransactionMode, merchant: Boolean?) {
        val inventory: Inventory
        val notPlayerInventory: Inventory

        if(mode == TransactionMode.BUY) {
            if(merchant!!) {
                inventory = activity.intent.getSerializableExtra("stock") as Inventory
            } else {
                inventory = GameManager.INSTANCE!!.currentPlanet.inventory
                Log.d("market", "buying ${inventory.inv.size}")
            }
        } else {
            inventory = GameManager.INSTANCE!!.player.ship.inventory
            Log.d("market", "selling ${inventory.inv.size}")
        } //if buying or selling

        if(merchant!!) {
            notPlayerInventory = activity.intent.getSerializableExtra("stock") as Inventory
        } else {
            notPlayerInventory = GameManager.INSTANCE!!.currentPlanet.inventory
        }

        inventory.inv.forEach {
            Log.d("market", "$mode triggered")
            val goodCard = makeGoodCard(it.key, it.value, mode, notPlayerInventory)
            linearLayout.addView(goodCard)
        } //for each item in inventory
    } //populateMarketList

    @SuppressLint("SetTextI18n")
    private fun makeGoodCard(good: Good, quantity: Int, mode: TransactionMode, notPlayerInventory: Inventory): CardView {
        val goodCard = CardView(activity)
        val cardLinear = LinearLayout(activity)
        val goodName = TextView(activity)
        val goodQuantity = TextView(activity)
        val goodPrice = TextView(activity)


        goodName.text = good.toString()
        goodName.textSize = 25.toFloat()
        goodQuantity.text = "x$quantity       "
        goodPrice.text = good.price(GameManager.INSTANCE!!.currentPlanet).toString()

        cardLinear.orientation = LinearLayout.HORIZONTAL
        cardLinear.addView(goodName)
        cardLinear.addView(goodQuantity)
        cardLinear.addView(goodPrice)

        goodCard.addView(cardLinear)
        goodCard.setOnClickListener {
            val dialog = GoodQuantityPickerDialog(activity, good, quantity, mode.provide(), notPlayerInventory)
            dialog.show()
        } //goodCard click Listener
        return goodCard
    } //makeGoodCard

} //MarketViewModel
