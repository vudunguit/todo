package io.tcn.todo.ui.buyList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.tcn.todo.data.Result
import io.tcn.todo.data.domain.GetBuyListUseCase
import io.tcn.todo.data.model.Item
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyListViewModel @Inject constructor(
  private val getBuyListUseCase: GetBuyListUseCase
): ViewModel() {

  private val _loading = MutableLiveData<Boolean>()
  val loading: LiveData<Boolean> = _loading
  private val _buyList = MutableLiveData<List<Item>>()
  val buyList: LiveData<List<Item>> = _buyList
  private val _message = MutableLiveData<String>()
  val message: LiveData<String> = _message

  init { getBuyList() }

  fun refresh(){
    val isLoading = _loading.value  ?: false
    if(!isLoading){
      getBuyList()
    }
  }
  private fun getBuyList(){
    _loading.postValue(true)
    viewModelScope.launch {
      when(val result = getBuyListUseCase.getBuyList()){
        is Result.Success -> {
          _loading.postValue(false)
          _buyList.postValue(result.data)
        }
        is Result.Error -> {
          _loading.postValue(false)
          _message.postValue("Failed to get call list: ${result.exception.message}")
        }
      }
    }
  }
}