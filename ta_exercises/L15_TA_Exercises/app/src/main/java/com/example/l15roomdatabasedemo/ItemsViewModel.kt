package com.example.l15roomdatabasedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * ItemsViewModel contains UI-related data and business logic.
 *
 * It receives data from the Repository and exposes it to the View as LiveData.
 * The Activity observes this LiveData and updates the RecyclerView.
 */
class ItemsViewModel(
    private val repository: ItemsRepository
) : ViewModel() {

    /**
     * Room emits Flow<List<Item>>.
     * asLiveData converts it to LiveData<List<Item>> for lifecycle-aware observation.
     */
    val items: LiveData<List<Item>> = repository.allItems.asLiveData()

    /**
     * Adds a new item.
     *
     * Empty input is ignored as basic error handling.
     */
    fun insert(title: String) {
        val cleanTitle = title.trim()

        if (cleanTitle.isEmpty()) {
            return
        }

        viewModelScope.launch {
            repository.insert(Item(title = cleanTitle))
        }
    }

    /**
     * Deletes the selected item.
     */
    fun delete(item: Item) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    /**
     * Clears completed items.
     */
    fun clearCompleted() {
        viewModelScope.launch {
            repository.clearCompleted()
        }
    }
}