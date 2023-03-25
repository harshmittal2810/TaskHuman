package com.harsh.taskhuman.ui.blogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.harsh.taskhuman.common.ViewBindingFragment
import com.harsh.taskhuman.databinding.FragmentBlogsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BlogsFragment : ViewBindingFragment<FragmentBlogsBinding>() {

    private val blogsViewModel: BlogsViewModel by viewModels()

    override fun provideBinding(inflater: LayoutInflater): FragmentBlogsBinding {
        return FragmentBlogsBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.textHome
        blogsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}