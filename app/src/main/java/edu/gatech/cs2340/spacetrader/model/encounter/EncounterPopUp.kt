package edu.gatech.cs2340.spacetrader.model.encounter

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import android.widget.TextView
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.util.TravelStatus
import edu.gatech.cs2340.spacetrader.views.UniverseMapActivity
abstract class EncounterPopUp(protected val view: AppCompatActivity, private val title: String, private val status: TravelStatus){

    protected var window: PopupWindow? = null

    abstract fun onChoice1(window: PopupWindow)

    abstract fun onChoice2(window: PopupWindow)

    abstract fun onChoice3(window: PopupWindow)

    abstract fun getDescription(): String

    abstract fun setChoice1(choice: Button)

    abstract fun setChoice2(choice: Button)

    abstract fun setChoice3(choice: Button)

    fun display() {
        val inflater = view.layoutInflater
        val statusView = inflater.inflate(R.layout.popup_encounter, null)

        val popUpWindow = PopupWindow( statusView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        if ( Build.VERSION.SDK_INT >= 21 ){
            popUpWindow.elevation = 5.0f
        }

        val choice1 = statusView.findViewById<Button>( R.id.button1 )
        val choice2 = statusView.findViewById<Button>( R.id.button2 )
        val choice3 = statusView.findViewById<Button>( R.id.button3 )

        choice1.setOnClickListener {
            onChoice1( popUpWindow )
        }

        choice2.setOnClickListener {
            onChoice2( popUpWindow )
        }

        choice3.setOnClickListener {
            onChoice3( popUpWindow )
        }

        val title = statusView.findViewById<TextView>( R.id.title )
        val desc = statusView.findViewById<TextView>( R.id.description )

        title.text = this.title
        desc.text = getDescription()
        setChoice1(choice1)
        setChoice2(choice2)
        setChoice3(choice3)

        popUpWindow.showAtLocation( statusView, Gravity.CENTER, 0, 0 )

        window = popUpWindow
    }

    fun setFinalDisplay(description: String) {
        val statusView = window!!.contentView

        val button1 = statusView.findViewById<Button>( R.id.button1 )
        val button2 = statusView.findViewById<Button>( R.id.button2 )
        val button3 = statusView.findViewById<Button>( R.id.button3 )

        button1.visibility = View.GONE
        button3.visibility = View.GONE

        button2.text = "Close"
        button2.setOnClickListener {
            window!!.dismiss()
            view.startActivity(Intent(view, UniverseMapActivity::class.java))
        }

        val desc = statusView.findViewById<TextView>( R.id.description )

        desc.text = description
    }
}