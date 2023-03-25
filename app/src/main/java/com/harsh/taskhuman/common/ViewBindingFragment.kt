package com.harsh.taskhuman.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.harsh.taskhuman.common.sharepref.PrefHelper

/**
 * Created by Harsh Mittal on 12/07/22.
 **/
abstract class ViewBindingFragment<Binding : ViewBinding> : Fragment() {

    private var _binding: Binding? = null
    protected val binding get() = _binding!!
    private lateinit var pre: PrefHelper

    abstract fun provideBinding(inflater: LayoutInflater): Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        _binding = provideBinding(inflater)
        pre = PrefHelper.getInstance(requireContext())
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}