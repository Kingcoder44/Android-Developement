package com.example.flashfeed.ui.fragments

import NewsVM
import android.content.Context
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flashfeed.adapters.NewsAdapter
import com.example.flashfeed.ui.FlashFeedActivity
import com.example.flashfeed.util.Constants
import com.example.flashfeed.util.Resource
import com.example.newsprojectpractice.R
import com.example.newsprojectpractice.R.*
import com.example.newsprojectpractice.databinding.FragmentHeadlinesBinding

class HeadlinesFragment : Fragment(R.layout.fragment_headlines) {

    lateinit var newsVM: NewsVM
    lateinit var newsAdapter: NewsAdapter
    lateinit var retryButtton : Button
    lateinit var errorText : TextView
    lateinit var itemHeadlinesError: CardView
    lateinit var binding : FragmentHeadlinesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHeadlinesBinding.bind(view)


        itemHeadlinesError = view.findViewById(R.id.itemHeadlinesError )
        val inflater = requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(layout.item_error,null)

        retryButtton = view.findViewById(R.id.retryButton)
        errorText = view.findViewById(R.id.errorText)

        newsVM = (activity as FlashFeedActivity).newsVM
        setupHeadlinesRecycler()

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article",it)
            }
            findNavController().navigate(R.id.action_headlinesFragment_to_articleFragment, bundle)
        }
        newsVM.headlines.observe(viewLifecycleOwner, Observer { response->
            when(response){
                is Resource.Success<*> ->{
                    hideProgressBar()
                    hideerrorMsg()
                    response.data?.let {
                        newsResponse->newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults/Constants.QUERY_PAGE_SIZE
                        isLastPage = newsVM.headLinesPage == totalPages
                        if (isLastPage){
                            binding.recyclerHeadlines.setPadding(0,0,0,0)
                        }
                    }
                }

                is Resource.Loading<*> ->{
                    showProgressBar()
                }
                is Resource.Error<*> -> {
                    hideProgressBar()
                    response.message?.let{
                        message->
                        Toast.makeText(activity,"Sorry Error: $message",Toast.LENGTH_SHORT).show()
                        showErrorMsg(message)
                    }
                }

            }
        })

        retryButtton.setOnClickListener{
            newsVM.getHeadlines("us")
        }
    }

    var isError = false
    var isloading = false
    var isLastPage = false
    var isScrolling = false

    private fun hideProgressBar(){
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isloading=false
    }
    private fun showProgressBar(){
        binding.paginationProgressBar.visibility = View.VISIBLE
        isloading=true
    }
    private fun hideerrorMsg(){
        itemHeadlinesError.visibility = View.INVISIBLE
        isError=false
    }
    private fun showErrorMsg(message: String){

        itemHeadlinesError.visibility = View.VISIBLE
        errorText.text = message
       isError=true
    }

    val scrollListener  = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPos =layoutManager.findFirstVisibleItemPosition()
            val itemCount = layoutManager.itemCount
            val childCoint  = layoutManager.childCount

            val isNoError = !isError
            val isNotLoadingAndNotLastPage = !isLastPage && !isloading
            val isAtLastItem = firstVisibleItemPos+childCoint>=itemCount
            val isNotAtBeginning = firstVisibleItemPos>=0
            val isTotalMoreThanVisible = itemCount >= Constants.QUERY_PAGE_SIZE
            val shouldPaginate = isNoError && isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if(shouldPaginate){
                newsVM.getHeadlines("us")
                isScrolling = false

            }


        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState==AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling=true
        }}
        private fun setupHeadlinesRecycler(){
            newsAdapter = NewsAdapter()
            binding.recyclerHeadlines.apply {
                adapter = newsAdapter
                layoutManager = LinearLayoutManager(activity)
                addOnScrollListener(this@HeadlinesFragment.scrollListener)
            }
    }
}