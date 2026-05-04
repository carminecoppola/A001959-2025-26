package com.example.l15roomdatabasedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.l15roomdatabasedemo.databinding.ActivityMainBinding

/**
 * MainActivity represents the View layer.
 *
 * Responsibilities:
 * - display the UI;
 * - observe LiveData from the ViewModel;
 * - send user events to the ViewModel;
 * - avoid direct database access.
 */
class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var itemAdapter: ItemAdapter

    companion object {
        private const val TAG = "L15_ROOM_DEMO"
    }

    /**
     * Manual dependency creation for a small academic project.
     *
     * Database → DAO → Repository → ViewModel
     */
    private val viewModel: ItemsViewModel by viewModels {
        val database = AppDatabase.getDatabase(applicationContext)
        val repository = ItemsRepository(database.itemDao())
        ItemsViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupListeners()
        setupObservers()
    }

    /**
     * Configures RecyclerView with LinearLayoutManager and Adapter.
     */
    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter { item ->
            viewModel.delete(item)
        }

        binding.recyclerViewItems.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)

            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL
                )
            )

            adapter = itemAdapter
        }
    }

    /**
     * Connects UI events to ViewModel methods.
     */
    private fun setupListeners() {
        binding.btnAdd.setOnClickListener {
            val title = binding.etItemTitle.text?.toString() ?: ""

            if (title.trim().isEmpty()) {
                binding.etItemTitle.error = "Title required"
                return@setOnClickListener
            }

            viewModel.insert(title)
            binding.etItemTitle.text?.clear()
        }

        binding.btnClearCompleted.setOnClickListener {
            viewModel.clearCompleted()
        }
    }

    /**
     * Observes database data exposed by the ViewModel.
     *
     * Room emits Flow, ViewModel converts it to LiveData,
     * and the Activity submits the list to the Adapter.
     */
    private fun setupObservers() {
        viewModel.items.observe(this) { items ->
            Log.d(TAG, "Items loaded: ${items.size}")
            itemAdapter.submitList(items)

            binding.tvEmptyState.text =
                if (items.isEmpty()) {
                    "No items stored in Room yet."
                } else {
                    "Tap an item to delete it."
                }
        }
    }
}