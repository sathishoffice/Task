package com.project.FlendzzTask

import com.google.gson.annotations.SerializedName

class EmployeeData(
    @SerializedName("name") val name : String?,
    @SerializedName("userName") val userName : String?,
    @SerializedName("serviceName") val serviceName : String?,
    @SerializedName("groupName") val groupName : String?,
    @SerializedName("blockNumber") val blockNumber : String?,
    @SerializedName("houseNumber") val houseNumber : String?,
    @SerializedName("userMobileNumber") val userMobileNumber : String?
)