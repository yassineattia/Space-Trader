package edu.gatech.cs2340.spacetrader.viewmodel

import edu.gatech.cs2340.spacetrader.entity.GameDifficulty
import edu.gatech.cs2340.spacetrader.util.DataType


class GameDifficultyViewModel: ConfigViewModel{
    private var difficulty = GameDifficulty.NORMAL

    fun setDifficulty(difficulty: GameDifficulty) {
        this.difficulty = difficulty
    }

    override fun processData(): Any {
        return difficulty
    }

    override fun getDataType(): DataType {
        return DataType.DIFFICULTY
    }
}
