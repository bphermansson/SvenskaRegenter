package com.example.SvenskaRegenter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nu.paheco.SvenskaRegenter.MainActivity
import nu.paheco.SvenskaRegenter.R

//class PeopleAdapter(context: Context) : RecyclerView.Adapter<PeopleAdapter.MyViewHolder>() {
class PeopleAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {
    var allRegents = mutableListOf<Regent>()
    var people = mutableListOf<String>("Arne", "Bertil", "Ceasar", "David")
    //private var onClickListener: OnClickListener? = null

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val uid : TextView
        val foreName : TextView
        val lastName : TextView
        val StartYear : TextView
        val StopYear : TextView
        val InfoText : TextView
        init {
            uid = view.findViewById(R.id.idTV)
            foreName = view.findViewById(R.id.forenameTV)
            lastName = view.findViewById(R.id.lastnameTV)
            StartYear = view.findViewById(R.id.startYearTV)
            StopYear = view.findViewById(R.id.stopYearTV)
            InfoText = view.findViewById(R.id.infoTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(MainActivity.logTag, "Skapa rad")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //val item = allRegents[position]
        Log.i(MainActivity.logTag, "RITA RAD " + position.toString())
        holder.foreName.text = people[position]
        holder.lastName.text = people[position]

        holder.itemView.setOnClickListener {
            if (onClickListener != null) {
                //onClickListener!!.onClick(position, item )
                val c = people[position]
                onClickListener.onClick(c)
            }
        }
    }
        override fun getItemCount(): Int {
            return people.size
        }

        // A function to bind the onclickListener.
        class OnClickListener(val clickListener: (meme: String) -> Unit) {
            fun onClick(meme: String) = clickListener(meme)
        }
    }



