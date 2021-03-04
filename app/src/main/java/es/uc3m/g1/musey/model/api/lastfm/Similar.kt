package es.uc3m.g1.musey.model.api.lastfm

import com.google.gson.annotations.SerializedName

//@JsonAdapter(LastFMSimilarResponse.Deserializer::class)
data class Similar (
    @SerializedName("similartracks") var similarTracks: SimilarTracks
) {
    data class SimilarTracks (
        @SerializedName("track") var track: List<Track>,
    )
}/* {
    class Deserializer: JsonDeserializer<LastFMSimilarResponse> {
        override fun deserialize(
            json: JsonElement?,
            typeOfT: Type?,
            context: JsonDeserializationContext?
        ): LastFMSimilarResponse {
            val list = with(json?.asJsonObject?.get("similartracks")?.asJsonObject) {
                this?.get("track")?.asJsonArray
            }
            return LastFMSimilarResponse(
                context?.deserialize(
                    list,
                    LastFMSimilarResponse::similarTracks::class.java
                ) ?: emptyList()
            )
            TODO("Not yet implemented")
        }

    }
}*/