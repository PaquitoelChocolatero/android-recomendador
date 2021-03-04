package es.uc3m.g1.musey.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import es.uc3m.g1.musey.databinding.ViewSongBinding
import es.uc3m.g1.musey.model.api.lastfm.Track

class TrackView(
    context: Context,
    attrs: AttributeSet,
): ConstraintLayout(context, attrs) {

    private val binding: ViewSongBinding = ViewSongBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
    )

    private var track: Track? = null

    init {
        setTrack(null)
    }

    fun setTrack(track: Track?) {
        this.track = track
        binding.artist.text = track?.artist?.name ?: ""
        binding.title.text  = track?.title ?: ""
        track?.album?.run {
            covers["medium"]?.run {
                Picasso.get().load(this).into(binding.cover)
            }
        }
    }
}