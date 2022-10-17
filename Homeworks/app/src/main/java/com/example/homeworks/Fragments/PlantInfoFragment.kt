package com.example.homeworks.Fragments

import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.homeworks.Models.PlantModel
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentPlantInfoBinding


class PlantInfoFragment : Fragment(R.layout.fragment_plant_info) {
    private var _binding: FragmentPlantInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var plant: PlantModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPlantInfoBinding.bind(view)
        setData()
        loadImg()
    }

    private fun loadImg() {
        Glide.with(this)
            .load(plant.imgUrl)
            .placeholder(R.drawable.ic_placeholder)
            .into(binding.imgPlant);
    }

    private fun setData() {
        with(binding) {
            btnBack.setOnClickListener{
                parentFragmentManager.popBackStack()
            }
            plant = requireArguments().getParcelable(PlantModel.PLANT_KEY)!!
            plant.species?.let {
                setTextTwoStyle(resources.getString(R.string.species), it, tvSpecies)
            }
            plant.otherNames?.let {
                setTextTwoStyle(resources.getString(R.string.other_names), it, tvOtherNames)
            }
            plant.botanicalName?.let {
                setTextTwoStyle(resources.getString(R.string.botanical_name), it, tvBotanicalName)
            }
            plant.description?.let {
                tvDescription.text = it
            }
            plant.plantName?.let{
                tvPlantName.text = it
            }
        }
    }

    private fun setTextTwoStyle(regular: String, medium: String, textView: TextView) {
        val text = regular + medium
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val spannableString = SpannableString(text)
            spannableString.setSpan(
                TypefaceSpan(resources.getFont(R.font.montserrat_regular)),
                0, regular.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                TypefaceSpan(resources.getFont(R.font.montserrat_medium)),
                regular.length, regular.length + medium.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                ForegroundColorSpan(resources.getColor(R.color.dark_green, null)),
                regular.length, regular.length + medium.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = spannableString
        }
        else
            textView.text = text
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ITEM_FRAGMENT_TAG = "ITEM_FRAGMENT_TAG"
        fun getInstance() = PlantInfoFragment()
    }
}