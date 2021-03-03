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
import es.uc3m.g1.musey.databinding.FragmentSearchBinding
import es.uc3m.g1.musey.viewModel.ViewModelSearch

/**
 * A fragment representing a list of Items.
 */
class FragmentRecommendation : Fragment() {

    private var columnCount = 1

    private lateinit var viewModelSearch: ViewModelSearch
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelSearch = ViewModelProvider(this).get(ViewModelSearch::class.java)
        arguments?.let {
            columnCount = 1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = CardSongSearchAdapter(viewModelSearch.list.value ?: emptyList())
            viewModelSearch.list.observe(viewLifecycleOwner, Observer { songs ->
                with (adapter as CardSongSearchAdapter) {
                    values = songs
                    notifyDataSetChanged()
                }
            })
            viewModelSearch.error_msg.observe(viewLifecycleOwner, Observer { error ->
                Toast.makeText(context.applicationContext, error, Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }
}