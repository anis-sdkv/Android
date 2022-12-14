package com.example.homeworks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.homeworks.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewPager()
        binding.btn.setOnClickListener {
            Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
            (binding.pager.adapter as PagerAdapter).preloadAll()
        }
    }

    private fun initViewPager() {
        val pager = binding.pager
        pager.adapter = PagerAdapter( Glide.with(this), lifecycleScope)
        pager.orientation = ViewPager2.ORIENTATION_VERTICAL
    }
}