package com.trekware.shoppinglistcx

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trekware.shoppinglistcx.data.ShoppingListDatabase
import com.trekware.shoppinglistcx.data.ShoppingListItem
import kotlinx.coroutines.launch

/**
 * Used to communicate between screens.
 */
class MainViewModel(context: Context) : ViewModel() {

    private val _currentScreen = MutableLiveData<Screens>(Screens.DrawerScreens.Grocery)
    private val dataSource = ShoppingListDatabase.getInstance(context.applicationContext as Application).shoppingListDatabaseDao

    val currentScreen: LiveData<Screens> = _currentScreen

    private fun getCategory() : Long {
        val category : Long =
            when (currentScreen.value?.category) {
                Screens.Category.GROCERY -> 1L
                Screens.Category.HOMEIMPROVEMENT -> 2L
                else -> 0
            }
        return category
    }

    fun getShoppingList() : LiveData<List<ShoppingListItem>> {
        return dataSource.getAllItemList(getCategory())
    }

    fun setCurrentScreen(screen: Screens) {
        _currentScreen.value = screen
    }

    private val _clickCount = MutableLiveData(0)
    val clickCount: LiveData<Int> = _clickCount

    fun updateClick(value: Int) {
        _clickCount.value = value
    }

    fun addItem(shoppingListItem: ShoppingListItem) {
        shoppingListItem.category = getCategory()
        viewModelScope.launch {
            dataSource.insert(shoppingListItem)
        }
    }

    fun delAll() {
        viewModelScope.launch {
            dataSource.clear()
        }
    }
}