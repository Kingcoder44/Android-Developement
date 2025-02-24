package com.example.flashfeed.ui.fragments

import NewsVM
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.flashfeed.ui.FlashFeedActivity
import com.example.newsprojectpractice.R
import com.example.newsprojectpractice.databinding.FragmentArticleBinding
import com.google.android.material.snackbar.Snackbar


class ArticleFragment : Fragment(R.layout.fragment_article) {

    lateinit var newsVM : NewsVM
    val args: ArticleFragmentArgs by navArgs()
    lateinit var binding : FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        newsVM = (activity as FlashFeedActivity).newsVM
        val article = args.article

        binding.wv.apply {
            webViewClient = WebViewClient()
            article.url?.let{
                loadUrl(it)
        }
        }
        binding.fab.setOnClickListener{
            newsVM.addToFavourites(article)
            Snackbar.make(view,"Added to favourirtes",Snackbar.LENGTH_SHORT).show()
        }
    }

}