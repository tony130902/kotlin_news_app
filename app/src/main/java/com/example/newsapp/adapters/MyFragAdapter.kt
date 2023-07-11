package com.example.newsapp.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsapp.ArticleNews
import com.example.newsapp.R
import com.example.newsapp.data.Article

class MyFragAdapter : RecyclerView.Adapter<MyFragAdapter.MyBusinessViewHolder>() {

    inner class MyBusinessViewHolder(itemView: View) : ViewHolder(itemView) {
        val articleImage: ImageView = itemView.findViewById(R.id.newsPic_Iv)
        val articleTitle: TextView = itemView.findViewById(R.id.newsTitle_tv)
        val articleSource: TextView = itemView.findViewById(R.id.tvSource)
        val articleDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val articlePublishDate: TextView = itemView.findViewById(R.id.tvPublishedAt)
        val newsRootView: View = itemView.findViewById(R.id.newsItemRootView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBusinessViewHolder {
        return MyBusinessViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.news_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val diffUtil = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffUtil)

    private var onItemClickListener: ((Article) -> Unit)? = null

    override fun onBindViewHolder(holder: MyBusinessViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(context).load(article.urlToImage).placeholder(R.drawable.img_1).into(holder.articleImage)
            holder.articleTitle.text = article.title
            holder.articleDescription.text = article.description
            holder.articlePublishDate.text = article.publishedAt
            holder.articleSource.text = article.source.name

//            setOnItemClickListener {
//                onItemClickListener?.let{it(article)}
//            }
            holder.newsRootView.setOnClickListener {
                val intent = Intent(context,ArticleNews::class.java)
                intent.putExtra("article",article.url)
                context.startActivity(intent)
//                Log.d("nitesh", "onViewCreated: ${article.url}")
            }

        }

    }

    fun setOnItemClickListener(listener:(Article) -> Unit){
        onItemClickListener = listener
    }

}

