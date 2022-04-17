package com.example.newsapplication.view.fragment.article

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.newsapplication.databinding.ArticleLayoutBinding
import com.example.newsapplication.view.base.BaseFragment
import com.example.newsapplication.view.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticleFragment : BaseFragment<ArticleLayoutBinding>() {

    private val viewModel by viewModel<ArticleViewModel>()

    override fun provideViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): ArticleLayoutBinding? {
        return ArticleLayoutBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLiveData()
        setupOnClickListener()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView(url: String?) {
        binding?.webView?.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("$url")
        }
    }

    private fun setupLiveData() {
        viewModel.article.observe(viewLifecycleOwner) {
            setupWebView(it?.url)
        }
    }

    private fun setupOnClickListener() {
        binding?.saveArticle?.setOnClickListener {
            viewModel.onSaveArticleClick()
        }
    }
}