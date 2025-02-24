package com.example.flashfeed.adapters

// Import necessary Android and RecyclerView components
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flashfeed.models.Article
import com.example.newsprojectpractice.R

// Adapter class for managing and displaying news articles in a RecyclerView
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>() {

    // ViewHolder class to hold item views for each news article
    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // View references (Incorrect placement: should be inside ViewHolder)
    lateinit var articleImage: ImageView
    lateinit var articleSource: TextView
    lateinit var articleTitle: TextView
    lateinit var articleDesc: TextView
    lateinit var articleDate: TextView

    // DiffUtil callback to optimize list updates in RecyclerView
    private val differCallback = object : DiffUtil.ItemCallback<Article>() {
        // Checks if two items are the same based on their unique URL
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        // Checks if the content of two items is the same
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    // AsyncListDiffer for handling item list updates efficiently
    val differ = AsyncListDiffer(this, differCallback)

    // Inflates the item layout and creates a ViewHolder for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        )
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // Listener for handling item clicks
    private var onItemClickListener: ((Article) -> Unit)? = null

    // Binds data to views for each item in the RecyclerView
    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]

        // Initialize view references for the current item (Incorrect placement)
        articleImage = holder.itemView.findViewById(R.id.article_img)
        articleSource = holder.itemView.findViewById(R.id.art_src)
        articleTitle = holder.itemView.findViewById(R.id.art_title)
        articleDesc = holder.itemView.findViewById(R.id.art_desc)
        articleDate = holder.itemView.findViewById(R.id.articleDateTime)

        // Bind article data to views
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(articleImage) // Load image using Glide
            articleSource.text = article.source?.name ?: "Unknown Source" // Handle null source
            articleTitle.text = article.title
            articleDesc.text = article.description
            articleDate.text = article.publishedAt

            // Set click listener to trigger onItemClickListener when an article is clicked
            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    // Method to set a click listener for items
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
}
