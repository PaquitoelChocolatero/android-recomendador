package es.uc3m.g1.musey.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import es.uc3m.g1.musey.R
import es.uc3m.g1.musey.databinding.FragmentSearchBinding
import es.uc3m.g1.musey.viewModel.ViewModelSearch


class FragmentSearch: Fragment() {

    private lateinit var viewModelSearch: ViewModelSearch
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModelSearch = ViewModelProvider(this).get(ViewModelSearch::class.java)
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
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
            view?.hideKeyboard()
        }

        binding.input.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModelSearch.search = binding.input.text.toString()
                view?.hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })

        return binding.root
    }
}