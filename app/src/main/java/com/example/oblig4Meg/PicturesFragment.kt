package com.example.oblig4Meg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.databinding.FragmentPicturesBinding
import com.example.oblig4Meg.model.ArtViewApplication
import com.example.oblig4Meg.model.ArtViewModel
import com.example.oblig4Meg.model.ArtViewModelFactory
import com.example.oblig4Meg.network.ArtPhoto

interface CellClickListener {
    fun onCellClickListener(photo:ArtPhoto)
}


class PicturesFragment : Fragment(), CellClickListener {

    private lateinit var binding: FragmentPicturesBinding


    private val shareViewModel: ArtViewModel by activityViewModels {
        ArtViewModelFactory(
            (activity?.application as ArtViewApplication).basketRepository
        )
    }

    lateinit var basket: Basket




    override fun onCellClickListener(photo: ArtPhoto) {
        shareViewModel.setCurrentPicture(photo)
        findNavController().navigate(R.id.action_picturesFragment_to_infoPictureFragment)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentPicturesBinding.inflate(inflater,container,false)
        binding = fragmentBinding
        binding.photosGrid.adapter = PhotoGridAdapter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = shareViewModel
        }
        binding
    }
}