package com.harsh.taskhuman.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.harsh.taskhuman.common.ViewBindingFragment
import com.harsh.taskhuman.databinding.FragmentDiscoverBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingsFragment : ViewBindingFragment<FragmentDiscoverBinding>() {

    private val bookingsViewModel: BookingsViewModel by viewModels()

    override fun provideBinding(inflater: LayoutInflater): FragmentDiscoverBinding {
        return FragmentDiscoverBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.textHome
        bookingsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}