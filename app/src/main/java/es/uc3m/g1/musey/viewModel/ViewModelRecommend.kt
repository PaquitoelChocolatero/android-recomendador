package es.uc3m.g1.musey.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import es.uc3m.g1.musey.model.Repository
import es.uc3m.g1.musey.model.api.lastfm.Track
import es.uc3m.g1.musey.R
import es.uc3m.g1.musey.model.api.lastfm.Artist
import kotlinx.coroutines.*

class ViewModelRecommend(
        private val app: Application
): TrackListViewModel(app) {
    override val repository: Repository = Repository(app.applicationContext)

    val error: MutableLiveData<String> = MutableLiveData()

    var recommend: Track? = null
    set(value) {
        field = value
        if (value != null) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    tracks = repository.getSimilar(value)
                } catch (e: javax.net.ssl.SSLHandshakeException) {
                    error.postValue(
                            app.applicationContext.getString(R.string.ssl_pin_error)
                    )
                }
            }
        } else {
            tracks = emptyList()
        }
    }

    init {
        recommend = Track("believe", Artist("cher"))
    }
}