package com.example.datastore

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.datastore.core.DataStore
import androidx.lifecycle.asLiveData
import com.example.datastore.Data.Singleton
import com.example.datastore.Data.TestData
import com.example.datastore.Data.TestDataSerializer
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException
import androidx.datastore.dataStore
import com.example.datastore.MainActivity.Companion.dataStore
import com.example.datastore.datastore.TestSingleTon
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.reduce
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    companion object{
        lateinit var singleTon:TestSingleTon
        lateinit var mContext: Context
        val Context.dataStore: DataStore<TestData> by dataStore(
            fileName = "my_data.pb",
            serializer = TestDataSerializer(),
        )
        private val _myData = MutableStateFlow<TestData?>(null)
        val myData = _myData.asStateFlow()

    }

    fun updateMyData(newData: TestData) {
        GlobalScope.launch {
            dataStore.updateData { newData }
        }
    }

    init {
        CoroutineScope(Dispatchers.Main).launch {
            Log.i("youwon","init")
            dataStore.data.collect {
                _myData.emit(it)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = this
        singleTon = TestSingleTon(this)

        var button = findViewById<Button>(R.id.button)
        var textInput = findViewById<EditText>(R.id.editText)

        var mainMenuListJson =
            this.assets.open("main/allMenuList.json")
                .reader().readText()

        var mainLayoutData =
            Gson().fromJson(mainMenuListJson, TestData::class.java)

        var data :JSONObject = JSONObject(mainMenuListJson)

        singleTon.inputElementsSetting(data)


        Singleton.TestData = mainLayoutData

        updateMyData(mainLayoutData)

//        CoroutineScope(Dispatchers.Main).launch {
//            var t = ApplicationClass.getInstance().getDataStore().pw_text.first()
////            if(t==null || t==""){
////                Log.i("youwon","no data")
////            }else{
////                Log.i("youwon","here "+t)
////                myData.collect{data->
////                    if(data!=null){
////                        Log.i("youwon","data "+data)
////                    }
////                }
////            }
//        }

        button.setOnClickListener {
            changeInput()
//            changeData("hello")
//            changeData(textInput.text.toString())

        }
    }


    fun changeData(data:String) {
        CoroutineScope(Dispatchers.Main).launch {
            ApplicationClass.getInstance().getDataStore().setPwText(data)
            ApplicationClass.getInstance().getDataStore().pw_text.asLiveData().observe(this@MainActivity){
            }
        }
    }

    fun changeInput() {
        singleTon.inputElementsRemove("inputElements")
    }




}