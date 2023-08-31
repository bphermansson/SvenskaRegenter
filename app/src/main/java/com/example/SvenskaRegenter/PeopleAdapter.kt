package com.example.SvenskaRegenter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nu.paheco.SvenskaRegenter.MainActivity
import nu.paheco.SvenskaRegenter.R
import nu.paheco.SvenskaRegenter.databinding.ActivityMainBinding

//class PeopleAdapter(val mainAct : MainActivity) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    //var regentToAdd = Regent(1, "l", "Oasis", 1999, 2121, "Info")

    //var people = mutableListOf<Regent>(regentToAdd)
    var people = mutableListOf<Regent>()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val firstName : TextView
        val lastName: TextView
        val startYear: TextView
        val stopYear: TextView
        val info: TextView

        init {
            firstName = view.findViewById(R.id.fornameTV)
            lastName = view.findViewById(R.id.lastnameTV)
            startYear = view.findViewById(R.id.startYearTV)
            stopYear = view.findViewById(R.id.stopYearTV)
            info = view.findViewById(R.id.infoTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.firstName.text = people[position].firstName
        holder.lastName.text = people[position].lastName
        holder.startYear.text = people[position].StartYear.toString()
        holder.stopYear.text = people[position].StopYear.toString()
        holder.info.text = people[position].InfoText
        //Log.i(MainActivity.logTag, "RITA RAD " + position.toString())

        //holder.personName.text = people[position]

        holder.itemView.setOnClickListener{
            //mainAct.clickRow((people[position]))
        }
    }

    override fun getItemCount(): Int {
        return people.size
    }



}