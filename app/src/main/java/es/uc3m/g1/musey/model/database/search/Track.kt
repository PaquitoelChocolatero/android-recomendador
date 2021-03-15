package es.uc3m.g1.musey.model.database.search

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Track (
        @PrimaryKey(autoGenerate = true)
        val track: Int,
        val title: String,
        val artist: String,
        val album: String,
)