package com.example.demoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.RawContacts.Data
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class MainActivity2 : AppCompatActivity() {

    companion object {
        const val Name_Extra = "name_extra"
        const val Id_Extra = "id_extra"
        const val Lat_Extra = "lat_extra"
        const val Lon_Extra = "lon_extra"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


    val name = intent.getStringExtra(Name_Extra)
    val id = intent.getStringExtra(Id_Extra)
    val latitude = intent.getStringExtra(Lat_Extra)
    val longitude = intent.getStringExtra(Lon_Extra)

        Toast.makeText(applicationContext,"${name}${id}${latitude} ${longitude}", Toast.LENGTH_LONG).show()

    }
    private fun fetchData() {
        val url = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=100370380d61481baec48a74a0260ad3"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,

            {
                val data = ContactsContract.Contacts.Data(
                    newsJsonObject.getString("name"),
                    newsJsonObject.getString("id"),
                    newsJsonObject.getString("latitude"),
                    newsJsonObject.getString("longitude")
                )
            }
}