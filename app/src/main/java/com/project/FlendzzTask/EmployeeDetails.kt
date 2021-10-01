package com.project.FlendzzTask

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.newtask.databinding.ActivityEmployeeDetailsBinding
import android.text.method.LinkMovementMethod

import android.R

import android.widget.TextView




class EmployeeDetails : AppCompatActivity() {

    private val activityEmployeeDetailsBinding by lazy { ActivityEmployeeDetailsBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityEmployeeDetailsBinding.root)

        activityEmployeeDetailsBinding.token.setText(intent.getStringExtra("houseNumber"))
        activityEmployeeDetailsBinding.name.setText(intent.getStringExtra("userName"))
        activityEmployeeDetailsBinding.address.setText(intent.getStringExtra("houseNumber")+" "+intent.getStringExtra("groupName"))
        activityEmployeeDetailsBinding.mobile.setText(intent.getStringExtra("userMobileNumber"))
        activityEmployeeDetailsBinding.email.setText(intent.getStringExtra("email")!!.toLowerCase())
        activityEmployeeDetailsBinding.business.setText(intent.getStringExtra("serviceName"))
        activityEmployeeDetailsBinding.business2.setText(intent.getStringExtra("houseNumber")+" "+intent.getStringExtra("groupName"))

        activityEmployeeDetailsBinding.mobile.setOnClickListener {
            val phoneIntent = Intent(Intent.ACTION_DIAL)
            phoneIntent.data = Uri.parse("tel:" + intent.getStringExtra("userMobileNumber"))
            startActivity(phoneIntent)
        }
        activityEmployeeDetailsBinding.email.setMovementMethod(LinkMovementMethod.getInstance());
        activityEmployeeDetailsBinding.email.setOnClickListener{
            val link = "mailto:" + intent.getStringExtra("email")
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(link)
            startActivity(openURL)
        }
    }
}
