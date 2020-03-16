package edu.gatech.cs2340.spacetrader.entity.inventory

import edu.gatech.cs2340.spacetrader.entity.Good
import edu.gatech.cs2340.spacetrader.util.Constants
import java.io.Serializable

class MerchantInventory: Inventory(), Serializable {
    override val inv: MutableMap<Good, Int> = mutableMapOf()
    override var cap: Int = Int.MAX_VALUE
    override var size: Int = 0

    //Generates inventory for the merchant
    init {
        for(g: Good in Good.values()) {
            inv[g] = (0..Constants.MAX_GOODS_PER_PLANET).random()
        } //for
    } //init
} //MerchantInventory