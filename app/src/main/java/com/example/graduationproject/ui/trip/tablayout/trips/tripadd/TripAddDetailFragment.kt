package com.example.graduationproject.ui.trip.tablayout.trips.tripadd

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.R
import com.example.graduationproject.data.TripDatabase
import com.example.graduationproject.databinding.FragmentTripAddDetailBinding
import com.example.graduationproject.model.trip.TripModel
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class TripAddDetailFragment : Fragment() {

    private lateinit var binding: FragmentTripAddDetailBinding
    val call1: Calendar = Calendar.getInstance()
    val call2: Calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTripAddDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bookmarkDatabase = TripDatabase.getTripDatabase(requireActivity().applicationContext)

        val sharedPreferences = activity?.getSharedPreferences("trip_add_detail_content", Context.MODE_PRIVATE)
        val imgUrl = sharedPreferences?.getString("img_src", "")
        val title = sharedPreferences?.getString("title", "")
        val country = sharedPreferences?.getString("country", "")
        val city = sharedPreferences?.getString("city", "")

        val dateSetListener1 = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                call1.set(Calendar.YEAR,year)
                call1.set(Calendar.MONTH,month)
                call1.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateDateInView1()
            }

        }
        val dateSetListener2 = object : DatePickerDialog.OnDateSetListener{
            override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                call2.set(Calendar.YEAR,year)
                call2.set(Calendar.MONTH,month)
                call2.set(Calendar.DAY_OF_MONTH,dayOfMonth)
                updateDateInView2()
            }

        }

        binding.apply {

            AddDate1Bottom.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    DatePickerDialog(activity!!,dateSetListener1,
                        call1.get(Calendar.YEAR),
                        call1.get(Calendar.MONTH),
                        call1.get(Calendar.DAY_OF_MONTH)).show()
                }
            })
            AddDate2Bottom.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    DatePickerDialog(activity!!,dateSetListener2,
                        call2.get(Calendar.YEAR),
                        call2.get(Calendar.MONTH),
                        call2.get(Calendar.DAY_OF_MONTH)).show()
                }
            })

            tripTitle.text = title
            tripCountry.text = "$country" + "$city"
            Picasso.get().load(imgUrl).resize(411, 450).into(TripimgDetail)

            AddTripBottom.setOnClickListener {
                val trip = TripModel(0,title.toString(),country.toString(),city.toString(),textViewDate1.text.toString(),textViewDate2.text.toString(),imgUrl.toString())
                bookmarkDatabase?.tripDao()?.insert(trip)

                findNavController().apply {
                    navigate(R.id.action_tripAddDetailFragment_to_trip_page)
                }
            }
        }
    }
    private fun updateDateInView1(){
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.US)
        binding.textViewDate1.text = sdf.format(call1.time)
    }
    private fun updateDateInView2(){
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat,Locale.US)
        binding.textViewDate2.text = sdf.format(call2.time)
    }
}