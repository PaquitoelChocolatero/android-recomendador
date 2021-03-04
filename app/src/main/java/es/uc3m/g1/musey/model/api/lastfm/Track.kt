package es.uc3m.g1.musey.model.api.lastfm

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName

import java.lang.reflect.Type

data class Track (
    @SerializedName("name")   var title:  String,
    @SerializedName("artist") var artist: Artist,
    @SerializedName("album")  var album:  Album? = null,
) {
    data class Wrapper (
            val track: Track
    )
}