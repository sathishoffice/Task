package com.project.FlendzzTask

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project.newtask.R
import java.util.ArrayList


class EmployeeAdapter(var context: Context,
                      var employee: ArrayList<EmployeeData>, val listener: (email: String) -> Unit) : RecyclerView.Adapter<EmployeeAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return employee.size
    }

    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var name = itemview.findViewById<TextView>(R.id.name)
        val email = itemview.findViewById<TextView>(R.id.email)


        fun bind(data: EmployeeData) {
            name.text = data.userName
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.employee_list_detail, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(employee[position])

        holder.email.setOnClickListener {
            listener(
                "email"
            )
        }
    }
}