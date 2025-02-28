package com.example.flashfeed.ui

import NewsVM
import android.os.Binder
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.flashfeed.db.ArticleDB
import com.example.flashfeed.repo.NewsRepo
import com.example.newsprojectpractice.R
import com.example.newsprojectpractice.databinding.ActivityNewsBinding

class FlashFeedActivity : AppCompatActivity() {

    lateinit var newsVM: NewsVM
    lateinit var binder: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binder = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binder.root)

        val newsrepo = NewsRepo(ArticleDB(this))
        val vmProviderFactory = NewsVMProviderFactory(application,newsrepo)
        newsVM = ViewModelProvider(this,vmProviderFactory).get(NewsVM::class.java)

        val navhostFragment = supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navhostFragment.navController
        binder.bottomNavigationView.setupWithNavController(navController)
    }
}