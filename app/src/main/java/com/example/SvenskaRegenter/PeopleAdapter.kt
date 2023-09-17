package com.example.SvenskaRegenter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nu.paheco.SvenskaRegenter.MainActivity
import nu.paheco.SvenskaRegenter.R

class PeopleAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    var allRegents = mutableListOf<Regent>()
    var people = mutableListOf<String>("Arne", "Bertil", "Ceasar", "David")
    //private var onClickListener: OnClickListener? = null

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val uid : TextView
        val foreName : TextView
        val lastName : TextView
        val regYears : TextView
        //val InfoText : TextView
        init {
            uid = view.findViewById(R.id.idTV)
            foreName = view.findViewById(R.id.forenameTV)
            lastName = view.findViewById(R.id.lastnameTV)
            regYears = view.findViewById(R.id.yearsTV)
            //InfoText = view.findViewById(R.id.infoTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(MainActivity.logTag, "Skapa rad")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(MainActivity.logTag, "RITA RAD " + position.toString())
        holder.foreName.text = allRegents[position].foreName
        holder.lastName.text = allRegents[position].lastName
        holder.regYears.text = allRegents[position].StartYear.toString() + " - " + allRegents[position].StopYear.toString()
        //holder.InfoText.text = allRegents[position].InfoText

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                val clickedId = allRegents[position].uid.toString()
                onClickListener.onClick(clickedId)
            }
        }
    }
        override fun getItemCount(): Int {
            return allRegents.size
        }

        // A function to bind the onclickListener.
        class OnClickListener(val clickListener: (meme: String) -> Unit) {
            fun onClick(meme: String) = clickListener(meme)
        }
    }



