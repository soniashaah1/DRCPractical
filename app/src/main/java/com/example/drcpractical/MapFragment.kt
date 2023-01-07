package com.example.drcpractical

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.drcpractical.database.DatabaseHelper
import com.example.drcpractical.database.PlaceTable
import com.example.drcpractical.databinding.MapFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapFragment : Fragment(), OnMapReadyCallback {
    private var _binding: MapFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mMap: GoogleMap
    private var places: MutableList<PlaceTable> = ArrayList()
    private var selectedPlace: PlaceTable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MapFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val supportMapFragment = SupportMapFragment.newInstance()
        childFragmentManager
            .beginTransaction()
            .add(R.id.map, supportMapFragment)
            .commit()

        supportMapFragment.getMapAsync(this::onMapReady)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        selectedPlace = getArguments()?.getParcelable<PlaceTable>("placeName") as PlaceTable
        mMap = googleMap
        if (selectedPlace != null) {
            selectedPlace?.let {
                val latlng = LatLng(it.lat.toDouble(), it.lan.toDouble())
                mMap.addMarker(
                    MarkerOptions()
                        .position(latlng)
                )
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLng(
                        LatLng(
                            it.lat.toDouble(),
                            it.lan.toDouble()
                        )
                    )
                )
                val latlngzoom = LatLng(it.lat.toDouble(), it.lan.toDouble())

                mMap.uiSettings.isZoomControlsEnabled = true
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlngzoom, 6f))
            }
        } else {
            //Show all
            places = DatabaseHelper.getAllData()
            if (places.size > 0) {
                places.forEach { place ->
                    val latlng = LatLng(place.lat.toDouble(), place.lan.toDouble())
                    mMap.addMarker(
                        MarkerOptions()
                            .position(latlng)
                    )
                }
                mMap.moveCamera(
                    CameraUpdateFactory.newLatLng(
                        LatLng(
                            places[0].lat.toDouble(),
                            places[0].lan.toDouble()
                        )
                    )
                )
                val latlngzoom = LatLng(places[0].lat.toDouble(), places[0].lan.toDouble())

                mMap.uiSettings.isZoomControlsEnabled = true
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlngzoom, 6f))
            }
        }
    }
}

