package com.example.lab_week_03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

interface CoffeeListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity(), CoffeeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Pastikan ID root layout ada (R.id.main)
        val rootView = findViewById<android.view.View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSelected(id: Int) {
        // Cek apakah fragment sudah ada di container
        val detailFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_detail) as? DetailFragment

        if (detailFragment != null) {
            detailFragment.setCoffeeData(id)
        } else {
            // fallback kalau fragment_detail belum ada (misalnya di layout hp kecil)
            val newFragment = DetailFragment()
            val bundle = Bundle()
            bundle.putInt("coffee_id", id)
            newFragment.arguments = bundle

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_detail, newFragment)
                .addToBackStack(null)
                .commit()
        }
    }
}
