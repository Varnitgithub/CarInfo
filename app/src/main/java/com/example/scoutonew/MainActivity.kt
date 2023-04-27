package com.example.scoutonew

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class MainActivity : AppCompatActivity(), adapter.myinterface, adapter.myinterfaceforImage {

    private lateinit var mViewmodel: viewmodel
    private lateinit var madapter: adapter
    private lateinit var results: ArrayList<String>
    private lateinit var resultsid: ArrayList<String>
    lateinit var selectedItemofId: String
    lateinit var selectedItemofMaker: String
    lateinit var mdatabase: database
    lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val spinner: Spinner = findViewById(R.id.spinner)
        val spinner2: Spinner = findViewById(R.id.spinner2)
        val logout = findViewById<Button>(R.id.logout)

        results = ArrayList()
        resultsid = ArrayList()
        firebaseAuth = FirebaseAuth.getInstance()

        mdatabase = database.getcontext(this)
        val repository = repository(dataobject.newobj, mdatabase.mydao(),mdatabase.newdao())

        madapter = adapter(this, this,this)
        val recyclerview: RecyclerView = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = madapter

        mViewmodel = ViewModelProvider(this, Viewmodelfactory(repository))[viewmodel::class.java]
        mViewmodel.viewModelLiveData.observe(this) { it ->
            Log.d("dddddddddaaaaaaaaaatttttttttaaaaaaaaaa", "onCreate: here is data${it}")

            for (i in 1..100) {

                results.add(it[i].Make_Name)
            }
            for (i in 1..100) {

                resultsid.add(it[i].Make_ID.toString())
            }
            val adapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                results
            )
            spinner.adapter = adapter

            val newadapter = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_dropdown_item,
                resultsid
            )
            spinner2.adapter = newadapter

            spinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedItemofMaker = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity, "click something", Toast.LENGTH_SHORT).show()
                }

            }

            spinner2.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    selectedItemofId = parent?.getItemAtPosition(position).toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Toast.makeText(this@MainActivity, "click something", Toast.LENGTH_SHORT).show()
                }

            }

            mViewmodel.livecarinfo.observe(this, Observer {
                madapter.updatelist(it as ArrayList<carInfo>)
            })

            var count = 0

            val addCarButton: Button = findViewById(R.id.addcarbutton)
            addCarButton.setOnClickListener {

                Toast.makeText(this, "clicked with $selectedItemofId and $selectedItemofMaker", Toast.LENGTH_SHORT).show()
                CoroutineScope(Dispatchers.IO).launch {
                    mViewmodel.givecarinfo(carInfo(count,selectedItemofId.toInt(), selectedItemofMaker))
                    count++
                }

            }

        }


        logout.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this,Login::class.java))
        }
    }

    override fun onclick(dataItem: carInfo, position: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            mViewmodel.delete(dataItem)
        }
    }

    override fun onclicked(dataItem: carInfo, position: Int) {
        val READ_REQUEST_CODE = 42 // you can choose any number you like

        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "image/*"
        }

        startActivityForResult(intent, 42)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 42 && resultCode == Activity.RESULT_OK) {
            data?.data?.also { uri ->
                val inputStream = contentResolver.openInputStream(uri)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                // use the bitmap as needed
            }
        }
    }

}