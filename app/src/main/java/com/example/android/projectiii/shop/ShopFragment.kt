package com.example.android.projectiii.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.projectiii.R
import com.example.android.projectiii.databinding.FragmentExpertBinding
import com.example.android.projectiii.databinding.FragmentShopBinding
import com.example.android.projectiii.track.TrackViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShopFragment : Fragment() {

    private val shopViewModel: ShopViewModel by viewModel()
    private lateinit var binding: FragmentShopBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shop,
            container,
            false
        )

        val adapter = ShopRecyclerViewAdapter(shopViewModel)
        adapter.submitList(shopViewModel.items)
        binding.shopItemsList.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.shopItemsList.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Shop"
    }
}
