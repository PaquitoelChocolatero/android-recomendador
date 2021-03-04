package es.uc3m.g1.musey.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import es.uc3m.g1.musey.databinding.FragmentRecommendationBinding
import es.uc3m.g1.musey.model.api.lastfm.Track
import es.uc3m.g1.musey.viewModel.ViewModelRecommend

/**
 * A fragment representing a list of Items.
 */
class FragmentRecommendation : Fragment() {

    private lateinit var viewModelRecommend: ViewModelRecommend
    private lateinit var binding: FragmentRecommendationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelRecommend = ViewModelProvider(this).get(ViewModelRecommend::class.java)
        arguments?.let {
            viewModelRecommend.recommend = it["recommend"] as Track?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        binding.searched.setTrack(viewModelRecommend.recommend)
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = CardSongSearchAdapter(viewModelRecommend.list.value ?: emptyList())
            viewModelRecommend.list.observe(viewLifecycleOwner, Observer { songs ->
                with (adapter as CardSongSearchAdapter) {
                    values = songs
                    notifyDataSetChanged()
                }
            })
            viewModelRecommend.error.observe(viewLifecycleOwner, Observer { error ->
                Toast.makeText(context.applicationContext, error, Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }
}