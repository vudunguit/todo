package io.tcn.todo.di

import android.content.Context
import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.tcn.todo.data.db.AppDatabase
import io.tcn.todo.data.network.ApiService
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

  @Singleton
  @Provides
  fun provideDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getInstance(
    context
  )

  @Provides
  fun provideApiService(@ApplicationContext context: Context): ApiService {
    val logging = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
      logging.level = HttpLoggingInterceptor.Level.BODY
    } else {
      logging.level = HttpLoggingInterceptor.Level.NONE
    }
    val okHttpBuilder = OkHttpClient.Builder()
      .addInterceptor(logging)
      .connectTimeout(1, TimeUnit.SECONDS)
      .writeTimeout(1, TimeUnit.SECONDS)
      .readTimeout(1, TimeUnit.SECONDS)
      .cache(Cache(context.cacheDir, 1024 * 1024 * 10)) //10mb
    val client = okHttpBuilder.build()
    return Retrofit.Builder()
      .client(client)
      .baseUrl(ApiService.ENDPOINT)
      .addConverterFactory(GsonConverterFactory.create(Gson()))
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .build()
      .create(ApiService::class.java)
  }
}