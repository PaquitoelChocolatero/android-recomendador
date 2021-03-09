package es.uc3m.g1.musey.model.api.lastfm

import android.os.Parcelable
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

import java.lang.reflect.Type

data class Track (
    @SerializedName("name")   var title:  String,
    @SerializedName("artist") var artist: Artist,
    @SerializedName("album")  var album:  Album? = null,
): Serializable {
    data class Wrapper (
            val track: Track
    )
}