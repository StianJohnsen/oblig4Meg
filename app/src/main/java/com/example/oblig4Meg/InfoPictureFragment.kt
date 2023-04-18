package com.example.oblig4Meg

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.databinding.FragmentInfoPictureBinding
import com.example.oblig4Meg.model.ArtViewApplication
import com.example.oblig4Meg.model.ArtViewModel
import com.example.oblig4Meg.model.ArtViewModelFactory
import com.example.oblig4Meg.network.ArtPhoto

interface CellClickListener2{
    fun onClickListener2(photo: ArtPhoto)
}
class InfoPictureFragment : Fragment(), CellClickListener2 {
    private lateinit var binding: FragmentInfoPictureBinding


    private val sharedViewModel: ArtViewModel by activityViewModels {
        ArtViewModelFactory(
            (activity?.application as ArtViewApplication).basketRepository
        )
    }

    lateinit var basket: Basket

    override fun onClickListener2(photo: ArtPhoto) {
        //sharedViewModel.insertPhotoBasket(photo)
        findNavController().navigate(R.id.action_infoPictureFragment_to_startFragment)
        println("photo_basket: ${sharedViewModel.photo_basket.value}")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentInfoPictureBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            infoPictureFragment = this@InfoPictureFragment
            viewModel = sharedViewModel
        }
    }


    fun putInBasket(artPhoto: ArtPhoto) {
        sharedViewModel.insertIntoBasket(artPhoto)
        findNavController().navigate(R.id.action_infoPictureFragment_to_startFragment)
    }

    fun goBackPage(){
        findNavController().navigate(R.id.action_infoPictureFragment_to_picturesFragment)
    }



}