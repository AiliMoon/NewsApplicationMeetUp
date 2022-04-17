package com.example.newsapplication.model.loading

import androidx.annotation.StringRes
import com.example.newsapplication.R

data class LoadingViewParams(
    val isVisible: Boolean,
    @StringRes val text: Int = R.string.progress_bar_status
)