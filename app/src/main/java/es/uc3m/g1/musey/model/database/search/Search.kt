package es.uc3m.g1.musey.model.database.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Search (
        @PrimaryKey
        val search: String,
)