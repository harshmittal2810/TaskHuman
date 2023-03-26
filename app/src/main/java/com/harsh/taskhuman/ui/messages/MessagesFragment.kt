package com.harsh.taskhuman.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.harsh.taskhuman.common.ViewBindingFragment
import com.harsh.taskhuman.databinding.FragmentMessagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessagesFragment : ViewBindingFragment<FragmentMessagesBinding>() {

    private val messageViewModel: MessagesViewModel by viewModels()

    override fun provideBinding(inflater: LayoutInflater): FragmentMessagesBinding {
        return FragmentMessagesBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.textHome
        messageViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }
}