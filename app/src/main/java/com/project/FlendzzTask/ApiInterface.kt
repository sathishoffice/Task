package com.project.FlendzzTask


import com.google.gson.JsonObject
import com.project.newtask.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface ApiInterface {


    //abcd
    @POST("serviceDetails.php")
    fun list(@Header("Authorization") token: String,
             @Body params: JsonObject
    ): Call<TotalData>

    companion object {

        private val okHttpClient by lazy {
            OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .build()
        }

        operator fun invoke(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(ApiInterface::class.java)
        }
    }
}
