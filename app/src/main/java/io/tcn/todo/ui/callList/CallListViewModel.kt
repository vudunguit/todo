package io.tcn.todo.ui.callList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.tcn.todo.data.Result
import io.tcn.todo.data.domain.GetCallListUseCase
import io.tcn.todo.data.model.CallItem
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallListViewModel @Inject constructor(
  private val getCallListUseCase: GetCallListUseCase
): ViewModel() {

  private val _loading = MutableLiveData<Boolean>()
  val loading: LiveData<Boolean> = _loading
  private val _callList = MutableLiveData<List<CallItem>>()
  val callList: LiveData<List<CallItem>> = _callList
  private val _message = MutableLiveData<String>()
  val message: LiveData<String> = _message

  init { getCallList() }

  fun refresh(){
    val isLoading = _loading.value ?: false
    if(!isLoading){
      getCallList()
    }
  }
  private fun getCallList(){
    _loading.postValue(true)
    viewModelScope.launch {
      when(val result = getCallListUseCase.getCallList()){
        is Result.Success -> {
          _loading.postValue(false)
          _callList.postValue(result.data)
        }
        is Result.Error -> {
          _loading.postValue(false)
          _message.postValue("Failed to get call list: ${result.exception.message}")
        }
      }
    }
  }
}