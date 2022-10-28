package io.tcn.todo.data.domain

import io.tcn.todo.data.Result
import io.tcn.todo.data.model.Item
import io.tcn.todo.data.network.ApiService
import javax.inject.Inject

class GetBuyListUseCase  @Inject constructor(
  private val apiService: ApiService
) {

  suspend fun getBuyList(): Result<List<Item>> {
    return try {
      val response = apiService.buyList().await()
      val body = response.body()
      if (response.isSuccessful && body != null) {
        val buyList =  body.filter {
          it.type == 1
        }
        Result.Success(buyList)
      } else {
        Result.Error(Exception(response.errorBody().toString()))
      }
    }catch (e: Exception){
      Result.Error(e)
    }
  }
}