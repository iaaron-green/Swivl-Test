package com.example.swivltestassigment.data.network

import com.example.swivltestassigment.Constants.Companion.BASE_URL
import com.example.swivltestassigment.Constants.Companion.TIMEOUT
import com.example.swivltestassigment.data.network.responce.UserApi
import com.example.swivltestassigment.data.network.responce.UserProfileApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


interface RestClient {
    @GET("users")
    fun getUsersList(
        @Query("since") page: Int? = null
    ): Call<List<UserApi>>

    @GET("users/{login}")
    fun getUser(
        @Path("login") login: String? = null
    ): Call<UserProfileApi>

    companion object {
        operator fun invoke(): RestClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
            var retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(RestClient::class.java)
        }
    }
}