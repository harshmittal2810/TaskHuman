package com.harsh.taskhuman.ui.discover

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harsh.taskhuman.data.Result
import com.harsh.taskhuman.ui.discover.model.Skill
import com.harsh.taskhuman.ui.discover.repository.DiscoverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DiscoverViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: DiscoverRepository,
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is discover Fragment"
    }
    val text: LiveData<String> = _text
    var isLoading: Boolean = false

    private val _skillsList = MutableLiveData<Result<List<Skill>>>()
    val skillsList: LiveData<Result<List<Skill>>> = _skillsList

    fun getExploreTaskHuman() {
        viewModelScope.launch(Dispatchers.IO) {
            _skillsList.postValue(Result.Loading)
            when (val result = repository.getExploreTaskHuman()) {
                is Result.Success -> {
                    isLoading = false
                    if (result.data != null) {
                        _skillsList.postValue(Result.Success(result.data.skills))
                    } else {
                        _skillsList.postValue(Result.Empty)
                    }
                }

                is Result.Failure -> {
                    isLoading = false
                    _skillsList.postValue(result)
                }

                else -> {
                    isLoading = false
                }
            }
        }
    }
}