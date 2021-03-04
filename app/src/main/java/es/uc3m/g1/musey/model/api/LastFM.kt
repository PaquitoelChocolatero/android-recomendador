package es.uc3m.g1.musey.model.api

import es.uc3m.g1.musey.model.api.lastfm.Similar
import es.uc3m.g1.musey.model.api.lastfm.Track

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFM {
    // TODO api key
    @GET("?method=track.getSimilar&api_key=77456d34d3c9185016ef4535935dccf3&limit=20&format=json")
    fun getSimilar(@Query("artist") artist: String, @Query("track") title: String): Call<Similar>
    @GET("?method=track.getInfo&api_key=77456d34d3c9185016ef4535935dccf3&limit=20&format=json")
    fun getInfo(@Query("artist") artist: String, @Query("track") title: String): Call<Track.Wrapper>
}