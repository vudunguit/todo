package io.tcn.todo.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.tcn.todo.data.model.Item

@Entity(tableName = SellEntity.TABLE_NAME)
data class SellEntity(
  @PrimaryKey
  @ColumnInfo(name = COLUMN_ID) val id: Int,
  @ColumnInfo(name = COLUMN_NAME) val name: String,
  @ColumnInfo(name = COLUMN_PRICE) val price: Long,
  @ColumnInfo(name = COLUMN_QUANTITY) val quantity: Int,
  @ColumnInfo(name = COLUMN_TYPE) val type: Int
){

  companion object{
    const val TABLE_NAME = "ItemToSell"
    const val COLUMN_ID = "id"
    const val COLUMN_NAME = "name"
    const val COLUMN_PRICE = "price"
    const val COLUMN_QUANTITY = "quantity"
    const val COLUMN_TYPE = "type"
  }
}
