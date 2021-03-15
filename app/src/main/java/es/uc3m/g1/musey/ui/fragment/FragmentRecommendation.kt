package es.uc3m.g1.musey.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.uc3m.g1.musey.R
import es.uc3m.g1.musey.databinding.FragmentRecommendationBinding
import es.uc3m.g1.musey.model.api.lastfm.Track
import es.uc3m.g1.musey.ui.adapter.CardSongSearchAdapter
import es.uc3m.g1.musey.viewModel.ViewModelRecommend

class FragmentRecommendation : Fragment() {

    companion object {
        fun newInstance(): FragmentRecommendation = FragmentRecommendation()
    }

    private lateinit var viewModelRecommend: ViewModelRecommend
    private lateinit var binding: FragmentRecommendationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModelRecommend = ViewModelProvider(this).get(ViewModelRecommend::class.java)

        arguments?.let {
            viewModelRecommend.track = it["track"] as Track?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searched.track = viewModelRecommend.track
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)

            adapter = CardSongSearchAdapter(
                viewModelRecommend.list.value ?: emptyList(),
                R.id.action_fragmentSongList_self
            )

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
        (binding.searched.parent as View).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentSongList_to_fragmentSearch)
        }
    }
}