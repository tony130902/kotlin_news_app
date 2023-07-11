package com.example.newsapp

import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newsapp.databinding.ActivityArticleNewsBinding

class ArticleNews : AppCompatActivity() {

    lateinit var binding: ActivityArticleNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_article_news)
        // For back button.
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val url = intent.getStringExtra("article")

        binding.webView.apply {
            // client helps to use web in within app.
            webViewClient = WebViewClient()
            if (url != null) {
                loadUrl(url)
            }
        }
    }

    // Back button.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home ->{
                if (binding.webView.canGoBack())
                    binding.webView.goBack()
                else finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}