package com.reachplc.interview.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.reachplc.interview.BeautyProductsApp
import com.reachplc.interview.R
import com.reachplc.interview.databinding.FragmentDetailBinding
import com.reachplc.interview.databinding.FragmentListBinding
import com.reachplc.interview.utils.Resource
import com.reachplc.interview.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.zip.Inflater

@AndroidEntryPoint
class ListFragment : Fragment() {

    private var listBinding: FragmentListBinding? = null
    private val binding get() = listBinding!!
    private lateinit var listAdapter: ListAdapter
    private val listModel: ListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = ListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listBinding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root


        listModel.products.observe(this.viewLifecycleOwner) { response ->


            response?.let {

                if (response.data != null) {
                    initRecycler()
                    listAdapter.setProduct(response.data)
                } else {
                    Snackbar.make(binding.main, "${response.error?.message}", Snackbar.LENGTH_LONG)
                        .show()
                }

            }

            binding.listProgressBar.isVisible =
                response is Resource.Loading && response.data.isNullOrEmpty()


        }

        setHasOptionsMenu(true)

        return view
    }


    private fun initRecycler() {
        binding.productRecyclerView.layoutManager = GridLayoutManager(this.requireContext(), 2)
        binding.productRecyclerView.setHasFixedSize(true)
        binding.productRecyclerView.adapter = listAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.menu, menu)

        val searchItem = menu.findItem(R.id.menu_search)
        val searchView = searchItem.actionView as? SearchView

        searchView?.onQueryTextChanged {
            query(it)
        }

    }


    private fun query(query: String) {

        val product = "%$query%"

        listModel.getQuery(product).observe(this, {

            listAdapter.setProduct(it)

            Log.d("search", "$it")

        })

    }


    override fun onDestroy() {
        super.onDestroy()
        listBinding = null
    }

}