package es.uc3m.g1.musey.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import es.uc3m.g1.musey.model.Song

class SongList(application: Application): AndroidViewModel(application) {
    val list: Array<Song> = arrayOf(
        Song("Párteme la cara", "C.Tangana", "El Madrileño"),
        Song("Ingobernable", "C.Tangana", "El Madrileño")
    )
}