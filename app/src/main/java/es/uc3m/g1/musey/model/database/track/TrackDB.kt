package es.uc3m.g1.musey.model.database.track

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class TrackDB {
    @PrimaryKey
    val search: String,
    val result: String
}