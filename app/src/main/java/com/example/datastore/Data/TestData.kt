package com.example.datastore.Data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TestData(
    @SerializedName("RD01") val RD01: TestDataListType,
    @SerializedName("signaturelist") val signaturelist: TestDataListType
): Serializable

class Singleton(){

    companion object{
        lateinit var TestData:TestData

//        fun setTestData(data:TestData){
//            TestData = data
//        }
    }

}