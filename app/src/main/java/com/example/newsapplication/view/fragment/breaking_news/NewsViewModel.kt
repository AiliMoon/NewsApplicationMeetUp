package com.example.newsapplication.view.fragment.breaking_news

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.usecase.news.GetBreakingNews
import com.example.domain.interactor.usecase.news.SearchNews
import com.example.domain.model.news.ArticleModel
import com.example.domain.model.news.NewsModel
import com.example.newsapplication.R
import com.example.newsapplication.view.base.BaseViewModel

class NewsViewModel(
    private val getBreakingNews: GetBreakingNews,
    private val searchNews: SearchNews
) : BaseViewModel() {

    private val _breakingNews = MutableLiveData<NewsModel>()
    val breakingNews: LiveData<NewsModel> get() = _breakingNews

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        getBreakingNews()
    }

    fun getBreakingNews() {
        showLoading()
        getBreakingNews.execute(viewModelScope) {
            handleResult(it) { result ->
                hideLoading()
                _breakingNews.postValue(result)
            }
        }
    }

    fun searchNews(searchQuery: String?) {
        if (searchQuery.isNullOrEmpty()) return
        showLoading()
        searchNews.execute(viewModelScope, searchQuery) {
            hideLoading()
            handleResult(it) { result ->
                _breakingNews.postValue(result)
            }
        }
    }

    fun onArticleClick(model: ArticleModel) {
        val bundle = Bundle().apply {
            putSerializable("news", model)
        }
        navigateFragment(R.id.action_newsFragment_to_articleFragment, bundle)
    }

    fun onSavedNewsClick() {
        navigateFragment(R.id.action_newsFragment_to_savedNewsFragment)
    }
}