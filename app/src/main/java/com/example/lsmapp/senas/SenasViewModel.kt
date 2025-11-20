package com.example.lsmapp.senas

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SenasViewModel : ViewModel() {

    private val _senas = MutableStateFlow<List<Sena>>(emptyList())
    val senas = _senas.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    init {
        loadSenas()
    }

    private fun loadSenas() {
        _senas.value = SenasData.senasMock
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun getFilteredSenas(): List<Sena> {
        return if (searchQuery.value.isBlank()) {
            senas.value
        } else {
            senas.value.filter { sena ->
                sena.nombre.contains(searchQuery.value, ignoreCase = true)
            }
        }
    }
}