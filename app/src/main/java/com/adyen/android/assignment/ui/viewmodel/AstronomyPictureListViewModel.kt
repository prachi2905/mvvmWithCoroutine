package com.adyen.android.assignment.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adyen.android.assignment.api.model.AstronomyPicture
import com.adyen.android.assignment.api.repo.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class AstronomyPictureListViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val showProgressbar = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val _astronomyPicture = MutableLiveData<List<AstronomyPicture>>()

    val astronomyPicture: LiveData<List<AstronomyPicture>>
        get() = _astronomyPicture

    init {
        getAstronomyPictureList()
    }

    fun getAstronomyPictureList() {
        showProgressbar.value = true
        viewModelScope.launch {
            val response = mainRepository.getAstronomyImageApiCall()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _astronomyPicture.postValue(response.body())
                    showProgressbar.value = false

                } else {
                    onError("Error : ${response.message()} ")
                    showProgressbar.value = false
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.postValue(message)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}