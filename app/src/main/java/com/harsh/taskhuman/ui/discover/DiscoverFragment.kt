package com.harsh.taskhuman.ui.discover

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.harsh.taskhuman.R
import com.harsh.taskhuman.common.ViewBindingFragment
import com.harsh.taskhuman.common.customview.ProgressAnimDialog
import com.harsh.taskhuman.common.util.AppExceptionHandler
import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.databinding.FragmentDiscoverBinding
import com.harsh.taskhuman.ui.discover.adapter.DiscoverAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DiscoverFragment : ViewBindingFragment<FragmentDiscoverBinding>() {

    private val discoverViewModel: DiscoverViewModel by viewModels()
    private var mProgressDialog: ProgressAnimDialog? = null

    @Inject
    lateinit var discoverAdapter: DiscoverAdapter

    override fun provideBinding(inflater: LayoutInflater): FragmentDiscoverBinding {
        return FragmentDiscoverBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            guidanceText()
            recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                adapter = discoverAdapter
            }
            recyclerView.showShimmer()

            setUpListener()
        }

        discoverViewModel.getExploreTaskHuman()
    }

    private fun setUpListener() {
        discoverViewModel.skillsList.observe(viewLifecycleOwner) {
            when (it) {
                Result.Empty -> {
                    binding.recyclerView.hideShimmer()
                    discoverAdapter.clearAll()
                }
                is Result.Failure -> {
                    binding.recyclerView.hideShimmer()
                    discoverAdapter.clearAll()
                }
                Result.Loading -> {
                    binding.recyclerView.showShimmer()
                }
                is Result.Success -> {
                    binding.recyclerView.hideShimmer()
                    discoverAdapter.updateDiscoverList(it.data ?: mutableListOf())
                }
            }
        }

        discoverAdapter.onAddRemoveClicked = { position, model, isFav ->
            Log.d("DiscoverFragment", "Pos: $position, Model: $model, isFav: $isFav")

            if (isFav) {
                discoverViewModel.addFav(model.displayTileName ?: "", model.dictionaryName ?: "")
                    .observe(viewLifecycleOwner) {
                        when (it) {
                            Result.Empty -> hideLoader()
                            is Result.Failure -> hideLoader()
                            Result.Loading -> showLoader()
                            is Result.Success -> {
                                hideLoader()
                                model.isFavorite = isFav
                                discoverAdapter.itemUpdated(position, model)
                            }
                        }
                    }
            } else {
                discoverViewModel.removeFav(model.displayTileName ?: "")
                    .observe(viewLifecycleOwner) {
                        when (it) {
                            Result.Empty -> hideLoader()
                            is Result.Failure -> hideLoader()
                            Result.Loading -> showLoader()
                            is Result.Success -> {
                                hideLoader()
                                model.isFavorite = isFav
                                discoverAdapter.itemUpdated(position, model)
                            }
                        }
                    }
            }
        }
    }

    private fun guidanceText() {
        val spannable = SpannableString(getString(R.string.guidance_title))
        spannable.setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    requireContext(), R.color.green
                )
            ),
            18,
            22,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE,
        )

        spannable.setSpan(
            UnderlineSpan(),
            18,
            22,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE,
        )

        binding.tvGuidance.text = spannable
    }

    fun showLoader() {
        try {
            mProgressDialog =
                ProgressAnimDialog.show(requireContext(), getString(R.string.loading), true, null)
        } catch (e: Exception) {
            AppExceptionHandler.handle(e)
        }
    }

    fun hideLoader() {
        try {
            if (mProgressDialog != null && mProgressDialog!!.isShowing) {
                mProgressDialog!!.dismiss()
            }
        } catch (e: Exception) {
            AppExceptionHandler.handle(e)
        }
    }
}