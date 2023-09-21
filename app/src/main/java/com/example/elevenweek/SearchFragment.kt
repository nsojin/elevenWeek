package com.example.elevenweek

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.elevenweek.data.ImageData
import com.example.elevenweek.data.SearchData
import com.example.elevenweek.databinding.FragmentSearchBinding
import com.example.elevenweek.retfofit.NetworkClient.api
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding: FragmentSearchBinding
    private lateinit var context1: Context
    private lateinit var adapter: SearchAdapter
    private lateinit var gridLayoutManager: StaggeredGridLayoutManager


    private var searchImage : ArrayList<SearchData> = ArrayList()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        context1 = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)

        setupView()
        setupListeners()

        return binding.root
    }

    private fun setupView(){
        gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.searchGridview.layoutManager = gridLayoutManager
        adapter = SearchAdapter(context1)
        binding.searchGridview.adapter = adapter
        binding.searchGridview.itemAnimator = null

        val recentSearch = Utils.getRecentSearch(requireContext())
        binding.editText.setText(recentSearch)
    }

    private fun setupListeners() {
        binding.imgSearch.setOnClickListener{
            Log.d("search","sj setonclick")
            val query = binding.editText.text.toString()
            if(query.isNotEmpty()){
                Utils.saveRecentSearch(requireContext(),query)
                adapter.clearImage()
                fetchImageResults(query)
                Log.d("search","sj fetch")
            }
            else{
                Toast.makeText(context1, "검색어를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchImageResults(query : String){
        api.Search(Contain.AUTH, query, "recency",1,80).enqueue(object : Callback<ImageData?> {
            override fun onResponse(call:Call<ImageData?>, response: Response<ImageData?>){
                response.body()?.meta?.let { meta ->
                    Log.d("search","sj ${response.body()?.meta?.totalCount}")
                    if(meta.totalCount > 0) {
                        response.body()!!.documents.forEach {document ->
                            val title = document.displaySitename
                            val dateTime = document.datetime
                            val url = document.thumbnailUrl
                            searchImage.add(SearchData(title, dateTime, url))
                        }
                    }
                }
                adapter.image = searchImage
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ImageData?>, t: Throwable) {
                Log.d("Search.f","sj fail")
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}