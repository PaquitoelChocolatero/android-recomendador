package es.uc3m.g1.musey.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.uc3m.g1.musey.R
import es.uc3m.g1.musey.databinding.FragmentSearchBinding
import es.uc3m.g1.musey.model.api.lastfm.Track
import es.uc3m.g1.musey.viewModel.ViewModelSearch

class FragmentSearch: Fragment() {

    private lateinit var viewModelSearch: ViewModelSearch
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelSearch = ViewModelProvider(this).get(ViewModelSearch::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        with(binding.results) {
            layoutManager = LinearLayoutManager(context)

            adapter = CardSongSearchAdapter(
                    viewModelSearch.list.value ?: emptyList(),
                    R.id.action_fragmentSearch_to_fragmentSongList
            )

            viewModelSearch.list.observe(viewLifecycleOwner, Observer { songs ->
                with (adapter as CardSongSearchAdapter) {
                    values = songs
                    notifyDataSetChanged()
                }
            })

            viewModelSearch.error.observe(viewLifecycleOwner, Observer { error ->
                Toast.makeText(context.applicationContext, error, Toast.LENGTH_SHORT).show()
            })
        }
        binding.button.setOnClickListener {
            viewModelSearch.search = binding.input.text.toString()
        }
        return binding.root
    }
}