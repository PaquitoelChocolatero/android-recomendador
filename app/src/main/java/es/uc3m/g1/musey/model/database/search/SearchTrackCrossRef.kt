package es.uc3m.g1.musey.model.database.search

import androidx.room.Entity

@Entity(primaryKeys = ["search", "track"])
data class SearchTrackCrossRef (
    val search: String,
    val track: Int,
)