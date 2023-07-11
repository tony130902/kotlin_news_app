package com.example.newsapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.newsapp.fragments.BusinessNewsFragment
import com.example.newsapp.fragments.SportsNewsFragment
import com.example.newsapp.fragments.TechnologyNewsFragment

class MyViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: androidx.lifecycle.Lifecycle)
    :FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
       return  when(position){
            0-> BusinessNewsFragment()
            1-> TechnologyNewsFragment()
            2-> SportsNewsFragment()
           else -> {Fragment()}
       }
    }
}