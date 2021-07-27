
package com.example.projet.API
import com.google.gson.annotations.SerializedName
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

//https://coronavirusapi-france.vercel.app/FranceLiveGlobalData
// Endpoint GET FranceLiveGlobalData
// Base URL = https://coronavirusapi-france.vercel.app/
// {"FranceGlobalLiveData":[{"code":"FRA","nom":"France","date":"2021-07-20","hospitalises":6912,"reanimation":876,"nouvellesHospitalisations":327,"nouvellesReanimations":61,"deces":85020,"gueris":388118,"source":{"nom":"OpenCOVID19-fr"},"sourceType":"opencovid19-fr"}]}

data class LiveData(
    @SerializedName("FranceGlobalLiveData")
    val content: List<LiveDataContent>,
)

data class LiveDataContent(
    val code: String,
    val nom: String,
    val date: String,
)

interface API {

    @GET("FranceLiveGlobalData")
    fun getGlobalDataAsync(): Deferred<LiveData>

    @POST("FranceLiveGlobalData")
    fun postInfo(
        @Body() element: String,
    ): Deferred<LiveData>

}

object Network {

    val api = Retrofit.Builder()
        .baseUrl("https://coronavirusapi-france.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(API::class.java)

}
