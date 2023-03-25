package com.harsh.taskhuman.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Harsh Mittal on 12/07/22.
 **/
abstract class ViewBindingDialogFragment<Binding : ViewBinding> : DialogFragment() {

    private var _binding: Binding? = null

    protected val binding get() = _binding!!

    abstract fun provideBinding(inflater: LayoutInflater): Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = provideBinding(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}