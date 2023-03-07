package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.ecommerse.domain.models.CategoryModel
import com.vladimir_tsurko.ecommerse.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.ecommerse.domain.models.base.BrandsPlaceHolder
import com.vladimir_tsurko.ecommerse.domain.models.base.FlashSalePlaceholder
import com.vladimir_tsurko.ecommerse.domain.models.base.LatestPlaceholder
import com.vladimir_tsurko.ecommerse.domain.models.base.ListItem
import com.vladimir_tsurko.ecommerse.domain.usecases.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLatestUseCase: GetLatestUseCase,
    private val getFlashSaleUseCase: GetFlashSaleUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSuggestionsUseCase: GetSuggestionsUseCase,
) : ViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>>
        get() = _data

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>>
        get() = _categories

    private val _suggestions = MutableLiveData<List<String>>()
    val suggestions: LiveData<List<String>>
        get() = _suggestions


    init {
        getCategories()
        getSuggestions()
        viewModelScope.launch {
            _data.postValue(getLoaders())
            _data.postValue(getData())
        }

    }

    private fun getSuggestions() = viewModelScope.launch {
        _suggestions.value = getSuggestionsUseCase().words
    }

    private fun getCategories() {
        _categories.value = getCategoriesUseCase()
    }

    private fun getLoaders(): List<ListItem> {
        return listOf(
            ProductsHorisontalItem(
                title = "Latest",
                products = IntRange(1, 3).map { LatestPlaceholder }
            ),
            ProductsHorisontalItem(
                title = "Flash Sale",
                products = IntRange(1, 2).map { FlashSalePlaceholder }
            ),
            ProductsHorisontalItem(
                title = "Brands",
                products = IntRange(1, 3).map { BrandsPlaceHolder }
            ))
    }

    private suspend fun getData(): List<ListItem> {

        val items = mutableListOf<ListItem>()
        val job1 = viewModelScope.launch {
            items.add(getLatestUseCase())
        }
        job1.join()
        val job2 = viewModelScope.launch {
            items.add(getFlashSaleUseCase())
        }
        job2.join()
        val brands = getBrandsUseCase()
        items.add(brands)
        return items
    }

}