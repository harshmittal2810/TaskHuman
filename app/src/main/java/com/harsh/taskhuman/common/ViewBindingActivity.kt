package com.harsh.taskhuman.common

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.harsh.taskhuman.common.sharepref.PrefHelper
import com.harsh.taskhuman.language.LocaleManager.getLocale
import com.harsh.taskhuman.language.LocaleManager.setLocale

/**
 * Created by Harsh Mittal on 12/07/22.
 **/

abstract class ViewBindingActivity<Binding : ViewBinding> : AppCompatActivity() {

    private lateinit var pre: PrefHelper
    private var _binding: Binding? = null
    protected val binding get() = _binding!!
    abstract fun provideBinding(inflater: LayoutInflater): Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pre = PrefHelper.getInstance(this)
        _binding = provideBinding(LayoutInflater.from(this))
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(setLocale(base!!))
    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            val locale = getLocale(resources)
            overrideConfiguration.setLocale(locale)
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }
}