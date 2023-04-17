package com.example.oblig4Meg

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.databinding.FragmentStartBinding
import com.example.oblig4Meg.model.ArtViewApplication
import com.example.oblig4Meg.model.ArtViewModel
import com.example.oblig4Meg.model.ArtViewModelFactory


class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding


    private val shareViewModel: ArtViewModel by activityViewModels {
        ArtViewModelFactory(
            (activity?.application as ArtViewApplication).database
                .basketDao()
        )
    }

    lateinit var basket: Basket


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = LinearAdapter{
            this.findNavController().navigate(R.id.action_startFragment_to_picturesFragment)
        }
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            startFragment = this@StartFragment
            photosLinear.adapter = adapter
        }

        shareViewModel.allItems.observe(this.viewLifecycleOwner){ items ->
            items.let {
                adapter.submitList(it)
            }
        }




    }

    fun choosePictures() {
        findNavController().navigate(R.id.action_startFragment_to_picturesFragment)
    }

    fun printHelloWord(){
        shareViewModel.addNewItem("heisann","Stor","Tre","400"
        ,"100","gegs"
        )
    }

    fun sendOrder() {

        var emptyString = StringBuilder()

        var mailString = mutableListOf<String>()

        shareViewModel.photo_basket.value?.forEachIndexed { index, it ->
            mailString.add("${index + 1}\nTitle: ${it.title}\nArtist: ${it.artist.name}\nCost: ${it.cost}Kr\n------------\n")

        }




        mailString.forEach {
            emptyString.append(it)
        }

        val intent = Intent(Intent.ACTION_SEND)
            .setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, emptyString.toString())
            .putExtra(Intent.EXTRA_EMAIL, arrayOf("artphotoagent@some.domain"))
            .putExtra(Intent.EXTRA_SUBJECT, "OrderSummary")


        if (activity?.packageManager?.resolveActivity(intent, 0) != null) {
            startActivity(intent)
        }


    }


}



