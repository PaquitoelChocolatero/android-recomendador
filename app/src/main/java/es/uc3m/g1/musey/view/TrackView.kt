package es.uc3m.g1.musey.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import com.squareup.picasso.PicassoProvider
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
    private val picasso = Picasso.get()

    var track: Track? = null
    set(track) {
        field = track
        binding.artist.text = track?.artist?.name ?: ""
        binding.title.text  = track?.title ?: ""
        var cover: String? = "https://lastfm.freetls.fastly.net/i/u/64s/2a96cbd8b46e442fc41c2b86b821562f.png"
        track?.album?.run {
            covers["medium"]?.run {
                if (this.isNotEmpty()) cover = this
            }
        }
        picasso.load(cover).into(binding.cover)
    }

    init {
        track = null
    }

}