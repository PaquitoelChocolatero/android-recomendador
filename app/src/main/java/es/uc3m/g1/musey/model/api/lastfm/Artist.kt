package es.uc3m.g1.musey.model.api.lastfm

import com.google.gson.annotations.SerializedName

data class Artist (
    @SerializedName("name") var name: String,
)