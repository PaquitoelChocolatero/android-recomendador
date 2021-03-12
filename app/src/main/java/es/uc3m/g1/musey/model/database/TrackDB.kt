package es.uc3m.g1.musey.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks")
data class TrackDB (
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val title: String,
        val artist: String,
        val album: String
)