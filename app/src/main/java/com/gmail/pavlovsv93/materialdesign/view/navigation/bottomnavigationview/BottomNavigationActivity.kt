package com.gmail.pavlovsv93.materialdesign.view.navigation.bottomnavigationview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.pavlovsv93.materialdesign.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}