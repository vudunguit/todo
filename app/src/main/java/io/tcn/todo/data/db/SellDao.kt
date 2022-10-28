package io.tcn.todo.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface SellDao {
  @Query("SELECT COUNT(*) FROM " + SellEntity.TABLE_NAME)
  suspend fun count(): Int

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(msg: SellEntity): Long

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertAll(msgs: List<SellEntity>): LongArray

  @Query("SELECT * FROM " + SellEntity.TABLE_NAME)
  suspend fun selectAll(): List<SellEntity>

  @Query("SELECT * FROM " + SellEntity.TABLE_NAME + " WHERE " + SellEntity.COLUMN_ID + " = :id")
  suspend fun selectById(id: Int): SellEntity?

  @Query("DELETE FROM " + SellEntity.TABLE_NAME + " WHERE " + SellEntity.COLUMN_ID + " = :id")
  suspend fun deleteById(id: Int): Int

  @Update
  suspend fun update(msg: SellEntity): Int
}