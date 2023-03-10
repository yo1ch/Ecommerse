package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.domain.models.DetailsModel
import com.vladimir_tsurko.domain.usecases.GetDetailsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase
) : ViewModel() {

    private val _details = MutableLiveData<DetailsModel>()
    val details: LiveData<DetailsModel>
        get() = _details

    private val _colors = MutableLiveData<List<com.vladimir_tsurko.domain.models.ColorModel>?>()
    val colors: LiveData<List<com.vladimir_tsurko.domain.models.ColorModel>?>
        get() = _colors

    private val _productQuantity = MutableLiveData<Int>(1)
    val productQuantity: LiveData<Int>
        get() = _productQuantity


    init {
        viewModelScope.launch {
            getDetails()
        }
    }

    fun addToCart() {
        val quantity = _productQuantity.value
        _productQuantity.value = quantity?.plus(1)
    }

    fun deleteFromCart() {
        val quantity = _productQuantity.value
        if (quantity != null && quantity > 1) {
            _productQuantity.value = quantity.minus(1)
        }

    }


    private suspend fun getDetails() {
        val details = getDetailsUseCase()
        _details.postValue(details)
        _colors.postValue(details.colors)
    }

    fun changeSelectedState(selectedId: Int) {
        val colors = _colors.value?.map {
            it.deepCopy()
        }
        colors?.forEach {
            it.isSelected = it.id == selectedId
        }
        _colors.value = colors

    }

}