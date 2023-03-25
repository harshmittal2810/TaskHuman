package com.harsh.taskhuman.ui.reconnect

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ReconnectViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is reconnect Fragment"
    }
    val text: LiveData<String> = _text
}