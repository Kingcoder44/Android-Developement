package com.example.flashfeed.ui

import NewsVM
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.flashfeed.repo.NewsRepo

class NewsVMProviderFactory(val app : Application,val newsrepo : NewsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsVM(app,newsrepo) as T
    }
}
