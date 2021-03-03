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
import es.uc3m.g1.musey.viewModel.ViewModelRecommendation

/**
 * A fragment representing a list of Items.
 */
class FragmentRecommendation : Fragment() {

    private var columnCount = 1

    private lateinit var viewModelRecommendation: ViewModelRecommendation
    private lateinit var binding: FragmentRecommendationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelRecommendation = ViewModelProvider(this).get(ViewModelRecommendation::class.java)
        arguments?.let {
            columnCount = 1
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecommendationBinding.inflate(inflater, container, false)
        with(binding.list) {
            layoutManager = LinearLayoutManager(context)
            adapter = CardSongSearchAdapter(viewModelRecommendation.list.value ?: emptyList())
            viewModelRecommendation.list.observe(viewLifecycleOwner, Observer { songs ->
                with (adapter as CardSongSearchAdapter) {
                    values = songs
                    notifyDataSetChanged()
                }
            })
            viewModelRecommendation.error_msg.observe(viewLifecycleOwner, Observer { error ->
                Toast.makeText(context.applicationContext, error, Toast.LENGTH_SHORT).show()
            })
        }
        return binding.root
    }
}