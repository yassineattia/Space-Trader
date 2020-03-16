package edu.gatech.cs2340.spacetrader.model.saving

import android.content.Context
import android.util.Log
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.util.Size
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class SerialSave: Savable {
    private val saveFileName = "saveFile"
    private val universeFileName = "universeSize"

    override fun save(manager: GameManager, size: Size?, context: Context): Boolean {
        try{
            var fileOutput = context.openFileOutput(saveFileName, Context.MODE_PRIVATE)
            val objectOutput = ObjectOutputStream(fileOutput)
            objectOutput.writeObject(manager)

            fileOutput = context.openFileOutput(universeFileName, Context.MODE_PRIVATE)
            objectOutput.writeObject(size)


            objectOutput.close()
            fileOutput.close()

            return true
        } catch(e: IOException) {
            Log.d("save", e.toString())
        } //try to save
        return false
    } //save

    override fun load(context: Context):Boolean {
        val manager:GameManager?
        val size:Size?

        try {
            var fileInput = context.openFileInput(saveFileName)
            val objectInput = ObjectInputStream(fileInput)
            manager = objectInput.readObject() as GameManager

            fileInput = context.openFileInput(universeFileName)
            size = objectInput.readObject() as Size

            objectInput.close()
            fileInput.close()

            GameManager.INSTANCE = manager
            GameManager.SIZE = size
            Log.d("load", "load successful")
            return true
        } catch(e: FileNotFoundException) {
            Log.d("load", e.toString())
        } //try to load
        return false
    } //load
} //SerialSave