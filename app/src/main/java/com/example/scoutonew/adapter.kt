package com.example.scoutonew

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapter(private var context: Context, private var listener: myinterface,private var mylistener:myinterfaceforImage): RecyclerView.Adapter<adapter.myviewholder>() {
    private var item = ArrayList<carInfo>()

    inner class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carmake : TextView = itemView.findViewById(R.id.carmake)
        val carmodel : TextView = itemView.findViewById(R.id.carmodel)
        val deletebutton: Button = itemView.findViewById(R.id.button_Deletecar)
        val addImage: Button = itemView.findViewById(R.id.button_addImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {



        val viewholder =  myviewholder(
            LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        )
        viewholder.deletebutton.setOnClickListener {
listener.onclick(item[viewholder.adapterPosition],viewholder.adapterPosition)
        }
        viewholder.addImage.setOnClickListener {
            mylistener.onclicked(item[viewholder.adapterPosition],viewholder.adapterPosition)
        }
        return viewholder
    }
    override fun getItemCount(): Int {
        return item.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updatelist(newlist:ArrayList<carInfo>){
        item.clear()
        item.addAll(newlist)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val currentitem = item[position]
        holder.carmake.text = currentitem.MakeID.toString()
        holder.carmodel.text = currentitem.MakeName

    }
    interface  myinterface{
        fun onclick(dataItem: carInfo, position: Int)
    }interface  myinterfaceforImage{
        fun onclicked(dataItem: carInfo, position: Int)
    }
}