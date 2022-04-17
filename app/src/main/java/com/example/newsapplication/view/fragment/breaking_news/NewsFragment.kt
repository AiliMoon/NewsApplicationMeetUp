package com.example.newsapplication.view.fragment.breaking_news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.databinding.BreakingNewsLayoutBinding
import com.example.newsapplication.view.base.BaseFragment
import com.example.newsapplication.view.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseFragment<BreakingNewsLayoutBinding>() {

    private val newsAdapter = NewsAdapter()
    private val viewModel by viewModel<NewsViewModel>()

    override fun provideViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun inflateView(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): BreakingNewsLayoutBinding? {
        return BreakingNewsLayoutBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupLiveData()
        setupOnClickListener()
        setupEditText()
    }

    private fun setupLiveData() {
        viewModel.breakingNews.observe(viewLifecycleOwner) {
            newsAdapter.addAllItem(it.articles?.toMutableList())
        }

        viewModel.showLoading.observe(viewLifecycleOwner) {
            binding?.progressBar?.isVisible = it.isVisible
        }
    }

    private fun setupRecyclerView() {
        binding?.recyclerView?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupOnClickListener() {
        binding?.refresh?.setOnClickListener {
            viewModel.getBreakingNews()
            binding?.progressBar?.isVisible = true
        }

        newsAdapter.setOnItemClickListener {
            viewModel.onArticleClick(it)
        }

        binding?.savedNews?.setOnClickListener {
            viewModel.onSavedNewsClick()
        }
    }

    private fun setupEditText() {
        binding?.searchEditText?.setOnEditorActionListener { text, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding?.searchEditText?.clearFocus()
                viewModel.searchNews(text.text.toString())
                val input: InputMethodManager =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                input.hideSoftInputFromWindow(binding?.searchEditText?.windowToken, 0)
            }
            true
        }
    }
}