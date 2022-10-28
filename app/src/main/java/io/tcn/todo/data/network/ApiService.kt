package io.tcn.todo.data.network

import io.tcn.todo.data.model.Item
import io.tcn.todo.data.model.CallItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
  @GET("imkhan334/demo-1/call")
  fun callList(): Deferred<Response<List<CallItem>>>

  @GET("imkhan334/demo-1/buy")
  fun buyList(): Deferred<Response<List<Item>>>

  companion object{
    const  val ENDPOINT = "https://my-json-server.typicode.com/"
  }
}