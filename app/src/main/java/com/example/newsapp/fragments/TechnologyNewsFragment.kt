package com.example.newsapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.MainActivity
import com.example.newsapp.R
import com.example.newsapp.Resource
import com.example.newsapp.adapters.MyFragAdapter
import com.example.newsapp.databinding.FragmentTechnologyNewsBinding
import com.example.newsapp.viewModels.NewsViewmodel

class TechnologyNewsFragment : Fragment(R.layout.fragment_technology_news) {

    private lateinit var binding: FragmentTechnologyNewsBinding
    private lateinit var viewModel: NewsViewmodel
    private lateinit var newsAdapter: MyFragAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTechnologyNewsBinding.bind(view)

        viewModel = MainActivity.viewsModel

        setupRecycleView()

        viewModel.technologyList.observe(viewLifecycleOwner, Observer { response ->
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
                is Resource.Loading -> {
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
        binding.technologyRv.layoutManager = LinearLayoutManager(activity)
        binding.technologyRv.adapter = newsAdapter
    }
}