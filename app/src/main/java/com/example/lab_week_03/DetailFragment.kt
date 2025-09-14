package com.example.lab_week_03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Button

class DetailFragment : Fragment() {

    private lateinit var coffeeTitle: TextView
    private lateinit var coffeeDesc: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coffeeTitle = view.findViewById(R.id.coffee_title)
        coffeeDesc = view.findViewById(R.id.coffee_desc)

        val coffeeId = arguments?.getInt(COFFEE_ID, 0) ?: 0
        setCoffeeData(coffeeId)

        val backButton = view.findViewById<Button>(R.id.back_button)
        backButton.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    fun setCoffeeData(id: Int) {
        when (id) {
            R.id.affogato -> {
                coffeeTitle.text = getString(R.string.affogato_title)
                coffeeDesc.text = getString(R.string.affogato_desc)
            }
            R.id.americano -> {
                coffeeTitle.text = getString(R.string.americano_title)
                coffeeDesc.text = getString(R.string.americano_desc)
            }
            R.id.latte -> {
                coffeeTitle.text = getString(R.string.latte_title)
                coffeeDesc.text = getString(R.string.latte_desc)
            }
            R.id.cappuccino -> {
                coffeeTitle.text = "Cappuccino"
                coffeeDesc.text = "Espresso dicampur dengan susu kukus dan buih susu."
            }
            R.id.espresso -> {
                coffeeTitle.text = "Espresso"
                coffeeDesc.text = "Kopi pekat khas Italia dengan rasa kuat."
            }
        }
    }

    companion object {
        private const val COFFEE_ID = "COFFEE_ID"

        fun newInstance(coffeeId: Int) =
            DetailFragment().apply {

                arguments = Bundle().apply {
                    putInt(COFFEE_ID, coffeeId)
                }
            }
    }
}
