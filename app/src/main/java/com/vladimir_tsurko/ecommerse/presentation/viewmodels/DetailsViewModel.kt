package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.ecommerse.domain.models.ColorModel
import com.vladimir_tsurko.ecommerse.domain.models.DetailsModel
import com.vladimir_tsurko.ecommerse.domain.usecases.GetDetailsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
): ViewModel() {

    private val _details = MutableLiveData<DetailsModel>()
    val details: LiveData<DetailsModel>
    get() = _details

    private val _colors = MutableLiveData<List<ColorModel>?>()
    val colors: LiveData<List<ColorModel>?>
        get() = _colors

    init{
        viewModelScope.launch {
            getDetails()
        }
    }

    private suspend fun getDetails(){
        val details = getDetailsUseCase()
        _details.postValue(details)
        _colors.postValue(details.colors)
    }

    fun changeSelectedState(selectedId: Int){
        val colors = _colors.value?.map {
            it.deepCopy()
        }
        colors?.forEach {
            it.isSelected = it.id == selectedId
        }
        _colors.value = colors

    }

}