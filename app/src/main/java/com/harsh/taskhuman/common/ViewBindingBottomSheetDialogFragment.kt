package com.harsh.taskhuman.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.harsh.taskhuman.R


/**
 * Created by Harsh Mittal on 12/07/22.
 **/
abstract class ViewBindingBottomSheetDialogFragment<Binding : ViewBinding> :
    BottomSheetDialogFragment() {

    private var _binding: Binding? = null

    protected val binding get() = _binding!!

    abstract fun provideBinding(inflater: LayoutInflater): Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val contextThemeWrapper = ContextThemeWrapper(activity, R.style.Theme_TaskHuman)
        _binding = provideBinding(inflater.cloneInContext(contextThemeWrapper))

        /*dialog?.setOnShowListener {
            if (dialog is BottomSheetDialog) {
                val bottomSheet: FrameLayout? = dialog?.findViewById(R.id.design_bottom_sheet)
                bottomSheet?.let {
                    val behavior = BottomSheetBehavior.from(bottomSheet)
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }
        }*/
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}