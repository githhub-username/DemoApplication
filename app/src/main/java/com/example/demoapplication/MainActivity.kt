package com.example.demoapplication

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.demoapplication.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val inputName = binding.nameInput.text.toString()
        val inputId = binding.idInput.text.toString()

        binding.send.setOnClickListener {

            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra(MainActivity2.Name_Extra,inputName)
            intent.putExtra(MainActivity2.Id_Extra,inputId)

            startActivity(intent)
        }

        binding.shareLocation.setOnClickListener {

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            fetchLocation()

        }

    }

    private fun fetchLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                101
            )
            return
        }

        task.addOnSuccessListener {
            if(it != null)
            {
                Toast.makeText(applicationContext,"${it.latitude} ${it.longitude}", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra(MainActivity2.Lat_Extra,it.latitude)
                intent.putExtra(MainActivity2.Lon_Extra,it.longitude)
            }
        }
    }
}