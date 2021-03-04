package es.uc3m.g1.musey.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val LASTFM_URL = "https://ws.audioscrobbler.com/2.0/"

    private val retrofit by lazy {
        /*  Optamos por la opción nativa de Android
            val httpBuilder = OkHttpClient.Builder()

            val certificatePinner = CertificatePinner.Builder()
                .add("ws.audioscrobbler.com", "sha256/Py8r09gmJxFrsGI4ef5Y+1C+xCqtPwUcOB9blDfvThw=")
                .build()

            val okHttpClient = OkHttpClient.Builder()
                .certificatePinner(certificatePinner)
                .build()
        */
        Retrofit.Builder()
            .baseUrl(LASTFM_URL)
            .addConverterFactory(GsonConverterFactory.create())
            //.client(okHttpClient)
            .build()
    }
    val lastfm: LastFM by lazy{
        retrofit.create(LastFM::class.java)
    }
}