package com.example.graduationproject.ui.trip.tablayout.bookmark

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.*
import com.example.graduationproject.data.BookmarkDatabase
import com.example.graduationproject.databinding.FragmentTabBookmarkBinding
import com.example.graduationproject.model.bookmark.BookmarkModel

class TabBookmarkFragment : Fragment() {

    private lateinit var binding: FragmentTabBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabBookmarkBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = activity?.getSharedPreferences("detail_content", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        val bookmarkDatabase = BookmarkDatabase.getBookmarkDatabase(requireActivity().applicationContext)
        val bookmark: ArrayList<BookmarkModel> = bookmarkDatabase?.bookmarkDao()?.getAllBookmark() as ArrayList<BookmarkModel>
        val adapter = BookmarkAdapter(bookmark){
            findNavController().apply {
                navigate(R.id.action_trip_page_to_detailFragment)
            }

            editor?.putString("title", it.title)
            editor?.putString("country", it.country)
            editor?.putString("city", it.city)
            editor?.putString("description", it.description)
            editor?.putString("img_src", it.images)
            editor?.apply()

        }

        binding.setVariable(BR.adapter,adapter)
    }
}