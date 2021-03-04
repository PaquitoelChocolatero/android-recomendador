package es.uc3m.g1.musey.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import es.uc3m.g1.musey.model.Repository
import es.uc3m.g1.musey.model.api.lastfm.Artist
import es.uc3m.g1.musey.model.api.lastfm.Track
import es.uc3m.g1.musey.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelSearch(application: Application): AndroidViewModel(application) {
    private val repository: Repository = Repository(application.applicationContext)

    val error: MutableLiveData<String> = MutableLiveData()
    val list: MutableLiveData<List<Track>> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                list.postValue(
                    repository.getTrack("Believe")
                )
            }catch (e: javax.net.ssl.SSLHandshakeException){
                error.postValue(
                    application.applicationContext.getString(R.string.ssl_pin_error)
                )
            }
        }
    }
}