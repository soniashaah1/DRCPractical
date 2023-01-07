package com.example.drcpractical

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drcpractical.adapter.OnItemClickListener
import com.example.drcpractical.adapter.PlacesListAdapter
import com.example.drcpractical.database.DatabaseHelper
import com.example.drcpractical.database.PlaceTable
import com.example.drcpractical.databinding.PlacesFragmentBinding
import com.example.drcpractical.modelplace.PlacesResponse
import com.example.drcpractical.modelplace.Results
import com.example.drcpractical.rest.ApiClient
import com.example.drcpractical.rest.ApiInterface
import com.google.android.material.snackbar.Snackbar

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlacesFragment : Fragment(), OnItemClickListener {
    private val baseURL = "https://maps.googleapis.com/maps/api/place/search/"
    private var _binding: PlacesFragmentBinding? = null
    private val binding get() = _binding!!

    private var places: List<PlaceTable> = ArrayList()
    private var adapter: PlacesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = PlacesFragmentBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService: ApiInterface = ApiClient.getClient(baseURL).create(ApiInterface::class.java)
        val call: Call<PlacesResponse> = apiService.getPlaces(
            "23.03744,72.566",
            "distance",
            "bakery",
            "true",
            "AIzaSyB2Az9gVUzQULUc55xQD9AE7gj9Ni5hvJk"
        )
        call.enqueue(object : Callback<PlacesResponse> {
            override fun onResponse(
                call: Call<PlacesResponse>,
                response: Response<PlacesResponse>
            ) {
                val statusCode = response.code()
                Log.e("response", "$statusCode")
                if (statusCode == 200) {
                    response.body().let {
                        places = getList(it.results)
                        initAdapter()
                    }
                }else{
                    Snackbar.make(binding.root, "Something went wrong...", Snackbar.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<PlacesResponse>, t: Throwable) {
                Snackbar.make(binding.root, "Something went wrong...", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun getList(results: List<Results>): List<PlaceTable> {
        for (result in results) {
            if (validate(result.name)) {
                addPlace(
                    result.name,
                    result.geometry.location.lat.toString(),
                    result.geometry.location.lng.toString(),
                    result.vicinity
                )
            }
        }
        return DatabaseHelper.getAllData()
    }

    fun initAdapter() {
        adapter = PlacesListAdapter(places, this)

        binding.placesReclyclerview.setHasFixedSize(true)
        binding.placesReclyclerview.layoutManager = LinearLayoutManager(requireActivity())
        binding.placesReclyclerview.adapter = adapter
    }

    private fun validate(placeName: String): Boolean {
        val places: MutableList<PlaceTable> = DatabaseHelper.getAllData()
        if (places.size > 0) {
            places.forEach { place ->
                if (placeName == place.name) {
                    return false
                }
            }
        }
        return true
    }

    private fun addPlace(placeName: String, lat: String, lan: String, vicinity: String) {
        val data = PlaceTable()
        data.name = placeName
        data.lat = lat
        data.lan = lan
        data.vicinity = vicinity

        val status = DatabaseHelper.insertData(data)
        if (status > 0) {
            Log.e("DB", "Added")
        } else {
            Log.e("DB", "Not Added")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(data: PlaceTable, position: Int) {
        val bundle = Bundle()
        bundle.putParcelable("placeName", data)
        findNavController().navigate(R.id.action_PlacesFragment_to_MapFragment, bundle)
    }
}
