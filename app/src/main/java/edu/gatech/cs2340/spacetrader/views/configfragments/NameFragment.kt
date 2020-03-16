package edu.gatech.cs2340.spacetrader.views.configfragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import edu.gatech.cs2340.spacetrader.R
import edu.gatech.cs2340.spacetrader.viewmodel.ConfigViewModel
import edu.gatech.cs2340.spacetrader.viewmodel.NameSelectionViewModel
import edu.gatech.cs2340.spacetrader.views.interfaces.ConfigViewModelProvider
import kotlinx.android.synthetic.main.fragment_name_selection.*

class NameFragment : Fragment(), ConfigViewModelProvider {
    private val nameVm = NameSelectionViewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_name_selection, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                nameVm.setName(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    override fun provideVM(): ConfigViewModel {
        return nameVm
    }
}