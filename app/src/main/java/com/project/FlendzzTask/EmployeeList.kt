package com.project.FlendzzTask

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.JsonObject
import com.project.newtask.BuildConfig
import com.project.newtask.databinding.ActivityEmployeeListBinding
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList


class EmployeeList : AppCompatActivity() {

    val empList: ArrayList<EmployeeData> = arrayListOf<EmployeeData>()
    private val activityEmployeeListBinding by lazy {
        ActivityEmployeeListBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityEmployeeListBinding.root)
        list()
    }

    private fun list() {
        val jsonObject = JsonObject()
        try {
            jsonObject.addProperty("token", 1209790684)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        ApiInterface().list(BuildConfig.SERVER_API_KEY, jsonObject)
            .enqueue(object : Callback<TotalData> {
                override fun onFailure(call: Call<TotalData>, t: Throwable) {
                    startActivity(Intent(this@EmployeeList, NoInternetActivity::class.java))
                }

                override fun onResponse(
                    call: Call<TotalData>, response: Response<TotalData>
                ) {
                    if (response.isSuccessful) {
                        empList.clear()
                        empList.addAll(response.body()!!.data)
                        activityEmployeeListBinding.employeelist.layoutManager =
                            LinearLayoutManager(this@EmployeeList)
                        activityEmployeeListBinding.employeelist.adapter =
                            EmployeeAdapter(this@EmployeeList, empList, ::onItemClickListener)
                        activityEmployeeListBinding!!.employeelist.addOnItemTouchListener(
                            RecyclerItemClickListener(this@EmployeeList,
                                activityEmployeeListBinding!!.employeelist,


                                object : RecyclerItemClickListener.OnItemClickListener {
                                    override fun onItemClick(view: View?, position: Int) {
                                        startActivity(
                                            Intent(this@EmployeeList, EmployeeDetails::class.java)
                                                .putExtra("name", empList[position].name)
                                                .putExtra("userName", empList[position].userName)
                                                .putExtra("serviceName", empList[position].serviceName)
                                                .putExtra("groupName", empList[position].groupName)
                                                .putExtra("blockNumber", empList[position].blockNumber)
                                                .putExtra("houseNumber", empList[position].houseNumber)
                                                .putExtra("userMobileNumber", "1234567890")
                                                .putExtra("email", "dummy@gmail.com")) }
                                    override fun onLongItemClick(view: View?, position: Int) {
                                    }
                                })
                        )
                    }
                }
            })


    }

    private fun onItemClickListener(data: String) {

        if (data != null) {
            val link = "mailto:" +"dummy@gmail.com"
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(link)
            startActivity(openURL)
        }
    }
}