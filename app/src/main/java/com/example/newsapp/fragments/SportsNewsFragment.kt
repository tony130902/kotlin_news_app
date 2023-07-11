package com.example.newsapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.Resource
import com.example.newsapp.adapters.MyFragAdapter
import com.example.newsapp.databinding.FragmentSportsNewsBinding
import com.example.newsapp.viewModels.NewsViewmodel

class SportsNewsFragment : Fragment(R.layout.fragment_sports_news) {

    private lateinit var viewModel: NewsViewmodel
    private lateinit var newsAdapter: MyFragAdapter
    private lateinit var binding: FragmentSportsNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSportsNewsBinding.bind(view)

        viewModel = MainActivity.viewsModel

        setupRecycleView()

        viewModel.sportsList.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e("TAG", "Error occurred : $message ")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun setupRecycleView() {
        newsAdapter = MyFragAdapter()
        binding.sportsRv.layoutManager = LinearLayoutManager(activity)
        binding.sportsRv.adapter = newsAdapter
    }
}