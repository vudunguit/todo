package io.tcn.todo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import timber.log.Timber
import java.util.concurrent.Executors

@Database(
  entities = [SellEntity::class],
  version = 1,
  exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
  companion object {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getInstance(context: Context): AppDatabase =
      INSTANCE ?: synchronized(this) {
        INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
      }

    private fun buildDatabase(context: Context) =
      Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "todo.db"
      )
        .addCallback(object : RoomDatabase.Callback() {
          override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            Timber.d("onCreate")
            Executors.newSingleThreadExecutor().execute {
              val itemToSell = listOf(
                SellEntity(1, "iPhoneX", 150000, 1, 2),
                SellEntity(2, "TV", 38000, 2, 2),
                SellEntity(3, "Table", 12000, 1, 2)
              )
              INSTANCE?.let {
                it.sellDao().insertAll(itemToSell)
              }

            }
          }
        })
        .build()

  }

  abstract fun sellDao(): SellDao
}