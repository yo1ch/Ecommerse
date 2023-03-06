package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.ecommerse.data.remote.dto.DetailsDto
import com.vladimir_tsurko.ecommerse.domain.models.ColorModel
import com.vladimir_tsurko.ecommerse.domain.models.DetailsModel
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem
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
            _details.postValue(getDetailsUseCase()!!)
            _colors.postValue(getDetailsUseCase().colors)
        }
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