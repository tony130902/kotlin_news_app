package com.example.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.MainActivity.Companion.binding
import com.example.newsapp.adapters.MyViewPagerAdapter
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.viewModels.NewsViewmodel
import com.example.newsapp.viewModels.NewsViewmodelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var binding: ActivityMainBinding
        lateinit var myViewPagerAdapter: MyViewPagerAdapter
        lateinit var viewsModel: NewsViewmodel
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        myViewPagerAdapter = MyViewPagerAdapter(supportFragmentManager,lifecycle)
        binding.viewPager2.adapter = myViewPagerAdapter
        setupTabLayout()

        val repository = NewsRepository()
        val viewModelProviderFactory = NewsViewmodelFactory(repository)
        viewsModel = ViewModelProvider(this,viewModelProviderFactory)[NewsViewmodel::class.java]

    }
}

private fun setupTabLayout(){
    binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.business_fragment))
    binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.technology_fragment))
    binding.tabLayout.addTab(binding.tabLayout.newTab().setText(R.string.sports_fragment))
    binding.tabLayout.tabGravity = TabLayout.GRAVITY_FILL

    TabLayoutMediator(binding.tabLayout,binding.viewPager2){tab,position ->
        when(position){
            0-> tab.text = "Business"
            1-> tab.text = "Technology"
            2-> tab.text = "Sports"
        }
    }.attach()
}