package edu.gatech.cs2340.spacetrader.model.transaction


enum class TransactionMode(private val transactional: Class<out Transactional>) {
    BUY(Buy::class.java),
    SELL(Sell::class.java);

    fun provide(): Transactional {
        return transactional.newInstance()
    }
}