package com.example.drcpractical

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.drcpractical.databinding.GifFragmentBinding
import com.example.drcpractical.modelgif.GIFResponse
import com.example.drcpractical.rest.ApiClient
import com.example.drcpractical.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.GridLayoutManager
import com.example.drcpractical.adapter.GIFRecyclerAdapter
import com.example.drcpractical.modelgif.Downsized_large
import com.google.android.material.snackbar.Snackbar


class GIFFragment : Fragment() {
    private val baseURL = "https://api.giphy.com/v1/gifs/"
    private var _binding: GifFragmentBinding? = null
    private var manager: GridLayoutManager? = null
    private var adapter: GIFRecyclerAdapter? = null
    private var gifsList: List<Downsized_large> = ArrayList()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = GifFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val apiService: ApiInterface =
            ApiClient.getClient(baseURL).create(ApiInterface::class.java)
        val call: Call<GIFResponse> = apiService.getGIF(
            "funny cat",
            "nqmcWlQvQTSArik1zSka5TEl4fAvTUH6",
        )

        call.enqueue(object : Callback<GIFResponse> {
            override fun onResponse(
                call: Call<GIFResponse>,
                response: Response<GIFResponse>
            ) {
                val statusCode = response.code()
                Log.e("response", "$statusCode")
                if (statusCode == 200) {
                    response.body().let {
                        val temp: MutableList<Downsized_large> = arrayListOf()
                        response.body().data.forEach { data ->
                            temp.add(data.images.downsized_large)
                        }
                        gifsList = temp
                        initAdapter()
                    }
                }else{
                    Snackbar.make(binding.root, "Something went wrong...", Snackbar.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GIFResponse>, t: Throwable) {
                Snackbar.make(binding.root, "Something went wrong...", Snackbar.LENGTH_SHORT).show()
            }
        })
    }

    private fun initAdapter() {
        manager = GridLayoutManager(context, 3)
        binding.gifReclyclerview.layoutManager = manager
        adapter = context?.let { GIFRecyclerAdapter(it, gifsList) }
        binding.gifReclyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}