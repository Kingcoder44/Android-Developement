import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.flashfeed.models.Article
import com.example.flashfeed.models.NewsResonse
import com.example.flashfeed.repo.NewsRepo
import com.example.flashfeed.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsVM(app: Application, private val newsRepo: NewsRepo) : AndroidViewModel(app) {

    val headlines: MutableLiveData<Resource<NewsResonse>> = MutableLiveData()
    var headLinesPage = 1
    var headLinesResponse: NewsResonse? = null

    val searchNews: MutableLiveData<Resource<NewsResonse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResonse? = null
    var newSearchQuery: String? = null
    var oldSearchQuery: String? = null

    init {
        getHeadlines("us")  // Default country code
    }

    fun getHeadlines(countryCode: String) = viewModelScope.launch {
        fetchHeadlines(countryCode)
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNewsInternet(searchQuery)
    }

    private fun handleHeadlinesResponse(response: Response<NewsResonse>): Resource<NewsResonse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                headLinesPage++

                if (headLinesResponse == null)
                    headLinesResponse = resultResponse
                else {
                    val oldArticles = headLinesResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(headLinesResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResonse>): Resource<NewsResonse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (searchNewsResponse == null || newSearchQuery != oldSearchQuery) {
                    searchNewsPage = 1
                    oldSearchQuery = newSearchQuery
                    searchNewsResponse = resultResponse
                } else {
                    searchNewsPage++
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun addToFavourites(article: Article) = viewModelScope.launch {
        newsRepo.upsert(article)
    }

    fun getFav() = newsRepo.getAllArticles()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepo.delete(article)
    }

    private fun internetConnect(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            android.content.Context.CONNECTIVITY_SERVICE
        ) as android.net.ConnectivityManager
        return connectivityManager.activeNetworkInfo?.isConnectedOrConnecting == true
    }

    fun fetchHeadlines(countryCode: String) {
        if (internetConnect()) {
            viewModelScope.launch {
                headlines.postValue(Resource.Loading())
                val response = newsRepo.getHeadlines(countryCode, headLinesPage)
                headlines.postValue(handleHeadlinesResponse(response))
            }
        } else {
            headlines.postValue(Resource.Error("No internet connection"))
        }
    }

    fun searchNewsInternet(searchQuery: String) {
        newSearchQuery = searchQuery
        if (internetConnect()) {
            viewModelScope.launch {
                searchNews.postValue(Resource.Loading())
                val response = newsRepo.searchNews(searchQuery, searchNewsPage)
                searchNews.postValue(handleSearchNewsResponse(response))
            }
        } else {
            searchNews.postValue(Resource.Error("No internet connection"))
        }
    }
}
