package es.uc3m.g1.musey.view

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import es.uc3m.g1.musey.R
import es.uc3m.g1.musey.model.Song

import es.uc3m.g1.musey.view.dummy.DummyContent.DummyItem

class SongListRecyclerViewAdapter(
    private val values: List<Song>
) : RecyclerView.Adapter<SongListRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.artist
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.findViewById(R.id.title)
        val contentView: TextView = view.findViewById(R.id.artist)

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }
}