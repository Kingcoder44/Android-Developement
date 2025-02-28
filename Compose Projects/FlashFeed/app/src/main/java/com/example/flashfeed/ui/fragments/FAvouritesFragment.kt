package com.example.flashfeed.ui.fragments

import NewsVM
import android.app.ProgressDialog.show
import android.content.ClipData.Item
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flashfeed.adapters.NewsAdapter
import com.example.flashfeed.ui.FlashFeedActivity
import com.example.newsprojectpractice.R
import com.example.newsprojectpractice.databinding.FragmentFAvouritesBinding
import com.example.newsprojectpractice.databinding.FragmentHeadlinesBinding
import com.google.android.material.snackbar.Snackbar


class FAvouritesFragment : Fragment(R.layout.fragment_f_avourites) {

    lateinit var newsViewModel : NewsVM
    lateinit var newsAdapter: NewsAdapter
    lateinit var binding: FragmentFAvouritesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFAvouritesBinding.bind(view)



        newsViewModel = (activity as FlashFeedActivity).newsVM
        setupFavouriteRecycler()
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_FAvouritesFragment_to_articleFragment, bundle)
        }
        val itemTouchHepler = object :ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
             val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                newsViewModel.deleteArticle(article)
                Snackbar.make(view,"Removed from favourites",Snackbar.LENGTH_LONG).apply {  setAction("Undo"){
                    newsViewModel.addToFavourites(article)
                }
                    show()
                }
            }

        }
        newsViewModel.getFav().observe(viewLifecycleOwner, Observer { articles->newsAdapter.differ.submitList(articles) })
    }

    private fun setupFavouriteRecycler(){
        newsAdapter = NewsAdapter()
        binding.favourites.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}