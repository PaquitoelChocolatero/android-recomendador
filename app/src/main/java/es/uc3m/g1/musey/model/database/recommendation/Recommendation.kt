package es.uc3m.g1.musey.model.database.recommendation

import androidx.room.Entity
import androidx.room.PrimaryKey
import es.uc3m.g1.musey.model.database.TrackDB

@Entity(tableName = "recommendations")
data class Recommendation (
    @PrimaryKey
    val input: TrackDB,
    val output: List<TrackDB>
)