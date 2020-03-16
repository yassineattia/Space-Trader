package edu.gatech.cs2340.spacetrader.viewmodel

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.generators.MapGenerator
import edu.gatech.cs2340.spacetrader.model.GameManager
import edu.gatech.cs2340.spacetrader.model.Planet
import edu.gatech.cs2340.spacetrader.views.PlanetMenuActivity
import edu.gatech.cs2340.spacetrader.views.TravelActivity
import edu.gatech.cs2340.spacetrader.views.UniverseMapActivity
import kotlinx.android.synthetic.main.activity_universe_map.*

class UniverseMapViewModel(private val view: UniverseMapActivity) {

    fun populateGridLayout(gridLayout: GridLayout, fuel: Double) {
        gridLayout.removeAllViews()
        gridLayout.columnCount = COLUMNS
        gridLayout.rowCount = ROWS
        gridLayout.clipChildren = false

        val layout = GameManager.SIZE!!
        val width = layout.width / ROWS
        val height = layout.height / COLUMNS
        val x: Float = 7.5f * width
        val y: Float = 7.5f * height

        val viewContainer = View(view)
        val layoutParams = RelativeLayout.LayoutParams((fuel * 50).toInt(), (fuel * 50).toInt())

        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT,  RelativeLayout.TRUE)

        viewContainer.layoutParams = layoutParams

        val drawable = ShapeDrawable(OvalShape())

        drawable.paint.color = Color.BLACK
        drawable.paint.strokeWidth = 5f
        drawable.paint.style = Paint.Style.STROKE

        viewContainer.background = drawable
        viewContainer.x = x - layoutParams.width / 2
        viewContainer.y = y - layoutParams.height / 2
        view.container.addView(viewContainer)

        val center = GameManager.INSTANCE!!.currentPlanet
        val gen = MapGenerator(GameManager.INSTANCE!!, center.coordinate, ROWS, COLUMNS)
        val map = gen.generate()
        Log.d("Planet", center.name)
        for (c in 0 until COLUMNS) {
            for (r in 0 until ROWS) {
                val planetHolder = PlanetHolder(view)

                gridLayout.addView(planetHolder)
                planetHolder.layoutParams = getLayoutParams(r, c)
            }
        }

        map.forEach {
            val child = gridLayout.getChildAt(getIndex(it.key.x, it.key.y))

            if (child == null) {
                Log.d("Planet", "Child is null @ ${it.key}")
                return
            }

            val holder = child as PlanetHolder

            holder.initialize(it.value, it.key.x, it.key.y, this)
        }
    } //populateGridLayout


    private fun getLayoutParams(row: Int, col: Int): GridLayout.LayoutParams {
        val param = GridLayout.LayoutParams()
        val layout = GameManager.SIZE!!
        val width = layout.width
        val height = layout.height

        param.height = height / ROWS
        param.width = width / COLUMNS
        param.setGravity(Gravity.CENTER)
        param.columnSpec = GridLayout.spec(col)
        param.rowSpec = GridLayout.spec(row)

        return param
    } //getLayoutParams

    private fun getIndex(row: Int, col: Int): Int {
        return row + (col * ROWS)
    } //getIndex

    private fun planetPushed(planet: Planet) {
        if(GameManager.INSTANCE!!.currentPlanet == planet) {
            Log.d("UniverseMapViewModel", "planet menu")
            view.startActivity(Intent(view, PlanetMenuActivity::class.java))
        } else {
            val intent = Intent(view, TravelActivity::class.java)
            val bundle = Bundle()
            bundle.putSerializable("Planet", planet)
            intent.putExtras(bundle)
            view.startActivity(intent)
            view.finish()
        }
    } //planetPushed

    private class PlanetHolder(private val view: AppCompatActivity): LinearLayout(view) {
        private val textView: TextView = TextView(view)
        private val image: ImageView = ImageView(view)

        init {
            this.orientation = LinearLayout.VERTICAL
            this.setPadding(10, 10, 10, 10)

            textView.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT)
            image.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT)

            this.addView(image)
        }

        fun initialize(planet: Planet, row: Int, col: Int, UniverseMapVM: UniverseMapViewModel) {
            val pos = calcTextPosition(row, col)

            textView.x = pos[0].toFloat()
            textView.y = pos[1].toFloat()
            textView.text = planet.name
            view.container.addView(textView)

            image.setImageResource(R.drawable.planet_button)
            image.setColorFilter(planet.solarSystem!!.color)
            image.setOnClickListener{
                UniverseMapVM.planetPushed(planet)
            } //image onClickListener

        }

        private fun calcTextPosition(row: Int, col: Int): IntArray {
            val pos = IntArray(2)
            val layout = GameManager.SIZE!!
            val width = layout.width / ROWS
            val height = layout.height / COLUMNS

            pos[0] = width * col
            pos[1] = height * row - (height / 4)

            return pos
        }
    }

    companion object {
        const val COLUMNS = 15
        const val ROWS = 15
    }
}