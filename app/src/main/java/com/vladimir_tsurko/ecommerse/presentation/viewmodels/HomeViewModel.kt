package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.ecommerse.domain.models.FlashSaleItem
import com.vladimir_tsurko.ecommerse.domain.models.LatestItem
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.FlashSalePlaceholder
import com.vladimir_tsurko.ecommerse.domain.models.base.LatestPlaceholder
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem
import com.vladimir_tsurko.ecommerse.domain.usecases.GetFlashSaleUseCase
import com.vladimir_tsurko.ecommerse.domain.usecases.GetLatestUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLatestUseCase: GetLatestUseCase,
    private val getFlashSaleUseCase: GetFlashSaleUseCase,
): ViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>> = _data

    init{

        viewModelScope.launch {
            _data.postValue(getLoaders())
            _data.postValue(getData())
        }

    }


    private  fun getLoaders(): List<ListItem>{
        return listOf(
            ProductsHorisontalItem(
                title = "Latest",
                products = IntRange(1,3).map{ LatestPlaceholder }
            ),
            ProductsHorisontalItem(
                title = "Flash Sale",
                products = IntRange(1,2).map{ FlashSalePlaceholder }
            ))
    }

    private suspend fun getData(): List<ListItem>{

        val items = mutableListOf<ListItem>()
        val job1 = viewModelScope.launch {
            items.add(getLatestUseCase())
        }
        job1.join()
        delay(2000L)
        val job2 = viewModelScope.launch {
            items.add(getFlashSaleUseCase())
        }
        job2.join()
        return items
    }

}