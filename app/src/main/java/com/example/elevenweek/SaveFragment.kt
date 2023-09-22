package com.example.elevenweek

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.elevenweek.data.SearchData
import com.example.elevenweek.databinding.FragmentSaveBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SaveFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SaveFragment : Fragment() {

    private lateinit var Context1 : Context
    private var binding: FragmentSaveBinding? = null
    private lateinit var adapter: SaveAdapter

    private var likedImage: List<SearchData> = listOf()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onAttach(context: Context){
        super.onAttach(context)
        Context1 = context
    }

    override fun onStart() {
        super.onStart()
        Log.d("save","sj savetragemnt onstart ")
    }

    override fun onResume() {
        super.onResume()
        val main = activity as MainActivity
        likedImage = main.likeimage
        adapter.image = likedImage.toMutableList()
        adapter.notifyDataSetChanged()
        Log.d("save","sj savetragemnt onresume ")
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

        Log.d("save","sj savetragemnt oncreateview ")

        adapter = SaveAdapter(Context1).apply {
            image = likedImage.toMutableList()
        }
        binding = FragmentSaveBinding.inflate(inflater, container, false).apply {
            saveRecyclerview.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            saveRecyclerview.adapter = adapter
        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SaveFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SaveFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}