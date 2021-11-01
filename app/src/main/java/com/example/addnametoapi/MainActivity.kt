package com.example.addnametoapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var btnAdd: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName)
        btnAdd = findViewById(R.id.btnAdd)
        val apiInterface = APINames().getName()?.create(APIInterface::class.java)
        btnAdd.setOnClickListener {
            val call: Call<namesListItem> = apiInterface!!.addNames(namesListItem(etName.text.toString(), 0))
            call?.enqueue(object: Callback<namesListItem?>{
                override fun onResponse(
                    call: Call<namesListItem?>,
                    response: Response<namesListItem?>
                ) {
                    Toast.makeText(this@MainActivity, "Name Added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<namesListItem?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "No Name Added", Toast.LENGTH_LONG).show()
                }

            })
        }
    }
}