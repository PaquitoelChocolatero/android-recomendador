package es.uc3m.g1.musey.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import es.uc3m.g1.musey.R
import es.uc3m.g1.musey.model.Repository
import es.uc3m.g1.musey.model.api.lastfm.Artist
import es.uc3m.g1.musey.model.api.lastfm.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class TrackListViewModel(
        private val app: Application
): AndroidViewModel(app) {

    protected abstract val repository: Repository

    val list:  MutableLiveData<List<Track>> = MutableLiveData()

    protected var tracks: List<Track> = emptyList()
        set(value) {
            field = value
            list.postValue(value)
            tracks.forEach {
                viewModelScope.launch(Dispatchers.IO) {
                    it.album = repository.getInfo(it)?.album
                    list.postValue(tracks)
                }
            }
        }
}