package es.uc3m.g1.musey.model.database.search

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SearchWithTracks (
    @Embedded val search: Search,
    @Relation(
        parentColumn = "search",
        entityColumn = "track",
        associateBy = Junction(SearchTrackCrossRef::class)
    )
    val tracks: List<Track>,
)