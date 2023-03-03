package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.ecommerse.domain.models.FlashSaleItem
import com.vladimir_tsurko.ecommerse.domain.models.LatestItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem
import com.vladimir_tsurko.ecommerse.domain.usecases.GetLatestUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLatestUseCase: GetLatestUseCase,
): ViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>> = _data

    init{
        viewModelScope.launch {
            val latestItem = getLatestUseCase()
            val flashSaleItem = getFlashSale()
            _data.postValue(listOf(latestItem, flashSaleItem))
        }

    }

    private suspend fun getFlashSale(): ListItem{
        return ProductsHorisontalItem(
            title = "Flash Sale",
            products = IntRange(1,20).map{ FlashSaleItem(it.toLong(), "Product title $it") }
        )
    }

}