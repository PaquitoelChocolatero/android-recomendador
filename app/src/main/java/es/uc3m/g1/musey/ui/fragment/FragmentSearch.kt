package es.uc3m.g1.musey.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.uc3m.g1.musey.R
import es.uc3m.g1.musey.databinding.FragmentSearchBinding
import es.uc3m.g1.musey.hideKeyboard
import es.uc3m.g1.musey.ui.adapter.CardSongSearchAdapter
import es.uc3m.g1.musey.viewModel.ViewModelSearch


class FragmentSearch: Fragment() {

    companion object {
        fun newInstance(): FragmentSearch = FragmentSearch()
    }

    private lateinit var viewModelSearch: ViewModelSearch
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelSearch = ViewModelProvider(this).get(ViewModelSearch::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            view.hideKeyboard()
        }

        binding.input.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModelSearch.search = binding.input.text.toString()
                view.hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })
    }
}