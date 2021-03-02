package es.uc3m.g1.musey.viewModel

import android.app.Application
import androidx.lifecycle.*
import es.uc3m.g1.musey.model.Repository
import es.uc3m.g1.musey.model.api.lastfm.Artist

import es.uc3m.g1.musey.model.api.lastfm.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SongListViewModel(application: Application): AndroidViewModel(application) {

    private val repository: Repository = Repository(application.applicationContext)

    val list: MutableLiveData<List<Track>> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            list.postValue(repository.getSimilar(
                Track(
                    "believe", Artist("cher")
                )
            ))
        }
    }

}