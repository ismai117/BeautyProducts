package com.reachplc.interview.ui.list

import androidx.lifecycle.*
import com.reachplc.interview.model.Model
import com.reachplc.interview.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {


    val products = repository.productsRepo().asLiveData()


    fun getQuery(product: String): LiveData<List<Model>> {
        return repository.getDatabaseQuery(product).asLiveData()
    }


}