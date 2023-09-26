package com.ivanojok.movieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.ivanojok.movieapp.R
import com.ivanojok.movieapp.vm.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {
    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bar = view.findViewById<ProgressBar>(R.id.progressBar)
        val recyclerView = view.findViewById<RecyclerView>(R.id.movie_recycler)

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.data.observe(viewLifecycleOwner) {
                when(it) {
                    is HomeViewModel.Response.Loading -> {
                        bar.visibility = View.VISIBLE
                    }
                    is HomeViewModel.Response.Failure -> {
                        Log.d("Error", "${it.error}")
                    }
                    is HomeViewModel.Response.Success -> {
                        Log.d("Success", "${it.movies}")
                        it.movies
                    }
                }
            }

        }
    }

}