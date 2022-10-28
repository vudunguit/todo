package io.tcn.todo.data.domain

import io.tcn.todo.data.Result
import io.tcn.todo.data.db.AppDatabase
import io.tcn.todo.data.model.Item
import javax.inject.Inject

class GetSellItemUseCase @Inject constructor(
  private val database: AppDatabase
) {
  suspend fun getSellList(): Result<List<Item>> {
    return try {
      val itemToSell = database.sellDao().selectAll().filter {
        it.type == 2
      }
      val items = itemToSell.map {
        Item(it.id, it.name, it.price, it.quantity, it.type)
      }
      Result.Success(items)
    } catch (e: Exception) {
      Result.Error(e)
    }
  }
}