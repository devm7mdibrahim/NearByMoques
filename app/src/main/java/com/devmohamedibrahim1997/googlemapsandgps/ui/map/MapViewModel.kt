package com.devmohamedibrahim1997.googlemapsandgps.ui.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.devmohamedibrahim1997.googlemapsandgps.network.APIClient
import com.devmohamedibrahim1997.googlemapsandgps.network.PlaceAPI
import com.devmohamedibrahim1997.googlemapsandgps.pojo.Mosque
import com.devmohamedibrahim1997.googlemapsandgps.pojo.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapViewModel : ViewModel() {

    val mosques = MutableLiveData<List<Result>>()

    fun getNearByMosques(type:String, location:String, apiKey:String): MutableLiveData<List<Result>> {
        val apiClient = APIClient()
        apiClient.getInstance()
            .create(PlaceAPI::class.java)
            .getNearbyMosques(type,location,3000,apiKey).enqueue(object : Callback<Mosque> {
                override fun onResponse(call: Call<Mosque>, response: Response<Mosque>) {
                    if(response.isSuccessful) {
                        mosques.value = response.body()?.results
                    }
                }

                override fun onFailure(call: Call<Mosque>, t: Throwable) {
                }
            })
        return mosques
    }
}