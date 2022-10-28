package io.tcn.todo.data.domain

import io.tcn.todo.data.Result
import io.tcn.todo.data.model.CallItem
import io.tcn.todo.data.network.ApiService
import javax.inject.Inject

class GetCallListUseCase @Inject constructor(
  private val apiService: ApiService
) {

  suspend fun getCallList(): Result<List<CallItem>>{
    return try {
      val response = apiService.callList().await()
      val body = response.body()
      if (response.isSuccessful && body != null) {
        Result.Success(body)
      } else {
        Result.Error(Exception(response.errorBody().toString()))
      }
    }catch (e: Exception){
      Result.Error(e)
    }
  }
}