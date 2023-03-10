package com.vladimir_tsurko.ecommerse.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vladimir_tsurko.domain.models.ProductsHorisontalItem
import com.vladimir_tsurko.domain.models.UserModel
import com.vladimir_tsurko.domain.models.base.BrandsPlaceHolder
import com.vladimir_tsurko.domain.models.base.FlashSalePlaceholder
import com.vladimir_tsurko.domain.models.base.LatestPlaceholder
import com.vladimir_tsurko.domain.models.base.ListItem
import com.vladimir_tsurko.domain.usecases.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getLatestUseCase: GetLatestUseCase,
    private val getFlashSaleUseCase: GetFlashSaleUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getSuggestionsUseCase: GetSuggestionsUseCase,
    private val getLoggedUserUseCase: GetLoggedUserUseCase,
) : ViewModel() {

    private val _data = MutableLiveData<List<ListItem>>()
    val data: LiveData<List<ListItem>>
        get() = _data

    private val _categories = MutableLiveData<List<com.vladimir_tsurko.domain.models.CategoryModel>>()
    val categories: LiveData<List<com.vladimir_tsurko.domain.models.CategoryModel>>
        get() = _categories

    private val _suggestions = MutableLiveData<List<String>>()
    val suggestions: LiveData<List<String>>
        get() = _suggestions

    private var _loggedUser = MutableLiveData<UserModel?>()
    val loggedUser: LiveData<UserModel?>
    get() = _loggedUser


    init {
        getCategories()
        getSuggestions()
        viewModelScope.launch {
            _data.postValue(getLoaders())
            _data.postValue(getData())
        }

    }

    suspend fun getLoggedUser() {
        _loggedUser.value = getLoggedUserUseCase()
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
        val jobLatest = viewModelScope.launch {
            items.add(getLatestUseCase())
        }
        jobLatest.join()
        val jobFlashSale = viewModelScope.launch {
            items.add(getFlashSaleUseCase())
        }
        jobFlashSale.join()
        val brands = getBrandsUseCase()
        items.add(brands)
        return items
    }

}