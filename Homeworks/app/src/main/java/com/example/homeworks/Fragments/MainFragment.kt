package com.example.homeworks.Fragments

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homeworks.Adapters.PlantsAdapter
import com.example.homeworks.DataStorage
import com.example.homeworks.MainActivity
import com.example.homeworks.Models.PlantModel
import com.example.homeworks.R
import com.example.homeworks.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding) {
            rvPlants.layoutManager = LinearLayoutManager(context)
            rvPlants.setHasFixedSize(true)

            rvPlants.adapter = PlantsAdapter().apply {
                items = DataStorage.plantList
                onItemClickListener = {
                    val fragment = PlantInfoFragment.getInstance()
                    fragment.arguments = Bundle().apply {
                        putParcelable(PlantModel.PLANT_KEY, it)
                    }

                    (requireActivity() as MainActivity).addFragment(
                        fragment,
                        PlantInfoFragment.ITEM_FRAGMENT_TAG,
                        true
                    )
                }
                colorOnVisit = resources.getColor(R.color.on_visit, null)
                indent = dpToPx(16)
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        val displayMetrics = resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val MAIN_FRAGMENT_TAG = "MAIN_FRAGMENT_TAG"
        fun getInstance() = MainFragment()
    }
}