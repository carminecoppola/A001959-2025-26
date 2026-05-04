package com.example.l15roomdatabasedemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Factory used to create ItemsViewModel with a Repository dependency.
 *
 * ViewModels with constructor parameters cannot be created by the default factory.
 */
class ItemsViewModelFactory(
    private val repository: ItemsRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
            return ItemsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}