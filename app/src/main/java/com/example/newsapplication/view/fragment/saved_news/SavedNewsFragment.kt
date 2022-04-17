package com.example.newsapplication.view.fragment.saved_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.adapter.NewsAdapter
import com.example.newsapplication.databinding.SavedNewsLayoutBinding
import com.example.newsapplication.view.base.BaseFragment
import com.example.newsapplication.view.base.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SavedNewsFragment : BaseFragment<SavedNewsLayoutBinding>() {

    private val viewModel by viewModel<SavedNewsViewModel>()
    private val newsAdapter = NewsAdapter()

    override fun provideViewModel(): BaseViewModel? {
        return viewModel
    }

    override fun inflateView(inflater: LayoutInflater, container: ViewGroup?): SavedNewsLayoutBinding? {
        return SavedNewsLayoutBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupLiveData()
        setupClickListener()
    }

    private fun setupLiveData() {
        viewModel.article.observe(viewLifecycleOwner) {
            newsAdapter.addAllItem(it.toMutableList())
        }

        viewModel.showLoading.observe(viewLifecycleOwner) {
            binding?.progressBar?.isVisible = it.isVisible
        }
    }

    private fun setupRecyclerView() {
        binding?.recyclerView?.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireContext())


            val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val article = newsAdapter.getItem(position)
                    viewModel.deleteArticle(article) {
                        newsAdapter.removeItem(position)
                    }
                }
            }

            val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
            itemTouchHelper.attachToRecyclerView(this)

        }
    }

    private fun setupClickListener() {
        newsAdapter.setOnItemClickListener {
            viewModel.onArticleClick(it)
        }
    }

}