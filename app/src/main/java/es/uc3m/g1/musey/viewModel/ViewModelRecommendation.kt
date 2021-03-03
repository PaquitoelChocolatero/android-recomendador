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

class ViewModelRecommendation(application: Application): AndroidViewModel(application) {

    private val repository: Repository = Repository(application.applicationContext)

    val error_msg: MutableLiveData<String> = MutableLiveData()
    val list: MutableLiveData<List<Track>> = MutableLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                list.postValue(
                    repository.getSimilar(
                        Track(
                            "believe", Artist("cher")
                        )
                    )
                )
            }catch (e: javax.net.ssl.SSLHandshakeException){
                error_msg.postValue(
                    application.applicationContext.getString(R.string.ssl_pin_error)
                )
            }
        }
    }

}