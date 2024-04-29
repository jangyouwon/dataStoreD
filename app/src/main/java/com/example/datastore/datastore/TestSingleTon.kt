package com.example.datastore.datastore

import android.content.Context
import android.util.Log
import com.example.datastore.Data.TestData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
class TestSingleTon(context:Context) {

    var datastoreClass = DataStoreClass(context)
    var inputElements = JSONArray()

    fun inputElementsSetting(input: JSONObject){
        inputElements = input.getJSONArray("inputElements")

        CoroutineScope(Dispatchers.Main).launch {
            datastoreClass.setPreferenceData("inputElements",inputElements.toString())
            Log.i("youwon","testSingleton setting "+inputElements)

        }
    }

    fun inputElementsRemove(key:String){
        CoroutineScope(Dispatchers.Main).launch {
            datastoreClass.resetPreferenceData(key)
//            var jsonObj = JSONObject(datastoreClass.getPreferenceData(key).first())
//            var jsonArr = JSONArray(datastoreClass.getPreferenceData(key).first())
            inputElements = JSONArray()

            var bb = (datastoreClass.getPreferenceData(key).first()==null)
            var cc = (datastoreClass.getPreferenceData(key).first()=="")
            Log.i("youwon","testSingleton remove "+bb)
            Log.i("youwon","testSingleton remove "+cc)
//            Log.i("youwon", "inputElements  ",inputElements.isNull())
        }
    }

}