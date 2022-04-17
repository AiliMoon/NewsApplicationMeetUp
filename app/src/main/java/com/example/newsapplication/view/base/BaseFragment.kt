package com.example.newsapplication.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.newsapplication.model.navigation.FragmentTransaction
import com.example.newsapplication.model.toast.ToastDuration

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    private var _binding:Binding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupLoadingViewModel()
        provideViewModel()?.let { observableViewModel ->
            lifecycle.addObserver(observableViewModel)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateView(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun navigateToFragment(fragmentTransaction: FragmentTransaction) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).navigateToFragment(fragmentTransaction)
        }
    }

    protected fun showToast(message: String, duration: ToastDuration) {
        Toast.makeText(requireContext(), message, duration.value).show()
    }

    protected fun popBackStack(@IdRes destinationId: Int, inclusive: Boolean = false) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).popBackStack(destinationId, inclusive)
        }
    }

    private fun setupLoadingViewModel() {
        provideViewModel()?.let { observableViewModel ->
            observableViewModel.setToast.observe(this) { messageAndDuration ->
                showToast(
                    message = messageAndDuration.first,
                    duration = messageAndDuration.second
                )
            }

            observableViewModel.navigateToFragment.observe(this) { transaction ->
                navigateToFragment(transaction)
            }

            observableViewModel.context = {
                requireContext()
            }

            observableViewModel.requiredArguments = {
                requireArguments()
            }

        }
    }

    abstract fun provideViewModel(): BaseViewModel?
    abstract fun inflateView(inflater: LayoutInflater, container: ViewGroup?): Binding?
}