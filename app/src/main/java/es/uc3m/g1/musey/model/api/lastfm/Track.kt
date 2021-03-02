package es.uc3m.g1.musey.model.api.lastfm

import com.google.gson.annotations.JsonAdapter

data class Track (
    var title:  String,
    var artist: Artist,
)
/*{
    class Deserializer: JsonDeserializer<LastFMTrack> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): LastFMTrack {
            with(json?.asJsonObject) {
                return LastFMTrack(
                    this?.get("name")?.asString ?: "",
                    with(this?.get("artist")?.asJsonObject) {
                        this?.get("name")?.asString ?: ""
                    },
                )
            }
        }
    }
}*/