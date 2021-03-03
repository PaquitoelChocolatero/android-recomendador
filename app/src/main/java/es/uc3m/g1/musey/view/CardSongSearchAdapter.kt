package es.uc3m.g1.musey.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import es.uc3m.g1.musey.databinding.CardSongBinding
import es.uc3m.g1.musey.model.api.lastfm.Track

class CardSongSearchAdapter(
    var values: List<Track>
) : RecyclerView.Adapter<CardSongSearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.setItem(item)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(private val view: CardSongBinding) : RecyclerView.ViewHolder(view.root) {

        fun setItem(item: Track) {
            view.title.text = item.title
            view.artist.text = item.artist.name
            item.covers["small"]?.run {
                Picasso.get().load(this).into(view.cover)
            }
        }
    }
}