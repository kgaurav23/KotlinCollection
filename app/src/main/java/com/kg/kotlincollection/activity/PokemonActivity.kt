package com.kg.kotlincollection.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.kg.kotlincollection.Pokemon
import com.kg.kotlincollection.R

class PokemonActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var accessLocationCode = 123
    private var location: Location? = null
    private var pokemonList = mutableListOf<Pokemon>()
    private var oldLocation: Location? = null
    val charmanderLocation = Pair(13.1000, 77.5413)
    val bulbasaurLocation = Pair(12.7064, 77.7000)
    val squirtleLocation = Pair(13.2019, 77.7582)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        checkPermission()
        loadPokemons()
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
    }

    private fun checkPermission() {
        if(Build.VERSION.SDK_INT >= 23) {
            if(ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), accessLocationCode)
                return
            }
        }
        getUserLocation()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode) {
            accessLocationCode -> {
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getUserLocation()
                } else {
                    Toast.makeText(this, "Denied Permission!! We can't get user location", Toast.LENGTH_LONG).show()
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    @SuppressLint("MissingPermission")
    private fun getUserLocation() {
        val myLocationListener = MyLocationListener()
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 3f, myLocationListener)

        MyThread().start()
    }

    inner class MyLocationListener: LocationListener {

        init {
            location = Location("Start")
            location!!.longitude = 0.0
            location!!.latitude = 0.0
        }

        override fun onLocationChanged(p0: Location?) {
            location = p0
        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

        override fun onProviderEnabled(p0: String?) {}

        override fun onProviderDisabled(p0: String?) {}
    }

    inner class MyThread: Thread() {

        init {
            oldLocation = Location("Start")
            oldLocation!!.longitude = 0.0
            oldLocation!!.latitude = 0.0
        }


        override fun run() {
            while (true) {
                try {
                    if(oldLocation?.distanceTo(location) == 0f) {
                        continue
                    }

                    oldLocation = location
                    runOnUiThread {
                        mMap.clear()
                        val sydney = LatLng(location!!.latitude, location!!.longitude)
                        mMap.addMarker(MarkerOptions()
                            .position(sydney)
                            .title("Me")
                            .snippet("Here is my location")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.mario)))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10f))

                        // Show Pokemons
                        for (pokemon in pokemonList) {
                            if(!pokemon.isCatch) {
                                val pokemonLoc = LatLng(pokemon.lat!!, pokemon.long!!)
                                mMap.addMarker(MarkerOptions()
                                    .position(pokemonLoc)
                                    .title(pokemon.name)
                                    .snippet(pokemon.des)
                                    .icon(BitmapDescriptorFactory.fromResource(pokemon.image!!)))
                            }
                        }
                    }

                    sleep(1000)

                } catch (ex: Exception){}
            }
        }
    }

    private fun loadPokemons() {

        //Bangalore location coordinates: (lat=13.0032, long=77.6664)
        //Patna location coordinates: (lat=25.5327, long=77.2413)
        //Delhi location coordinates: (lat=28.5064, long=77.2249)
        //Mumbai location coordinates: (lat=18.9601, long=72.9382)
        pokemonList.add(
            Pokemon("Charmander", "Charmander living in Patna", R.drawable.charmander,
            55.0, charmanderLocation.first, charmanderLocation.second))
        pokemonList.add(Pokemon("Bulbasaur", "Bulbasaur living in Delhi", R.drawable.bulbasaur,
            90.0, bulbasaurLocation.first, bulbasaurLocation.second))
        pokemonList.add(Pokemon("Squirtle", "Squirtle living in Mumbai", R.drawable.squirtle,
            33.5, squirtleLocation.first, squirtleLocation.second))
    }
}
