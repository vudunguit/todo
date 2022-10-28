package io.tcn.todo.ui.sellList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.tcn.todo.data.Result
import io.tcn.todo.data.domain.GetSellItemUseCase
import io.tcn.todo.data.model.Item
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellListViewModel @Inject constructor(
  private val getSellItemUseCase: GetSellItemUseCase
): ViewModel(){
  private val _loading = MutableLiveData<Boolean>()
  val loading: LiveData<Boolean> = _loading
  private val _sellList = MutableLiveData<List<Item>>()
  val sellList: LiveData<List<Item>> = _sellList
  private val _message = MutableLiveData<String>()
  val message: LiveData<String> = _message

  init { getSellList() }

  fun refresh(){
    val isLoading = _loading.value ?: false
    if(!isLoading){
      getSellList()
    }
  }
  private fun getSellList(){
    _loading.postValue(true)
    viewModelScope.launch {
      when(val result = getSellItemUseCase.getSellList()){
        is Result.Success -> {
          _loading.postValue(false)
          _sellList.postValue(result.data)
        }
        is Result.Error -> {
          _loading.postValue(false)
          _message.postValue("Failed to get call list: ${result.exception.message}")
        }
      }
    }
  }
}