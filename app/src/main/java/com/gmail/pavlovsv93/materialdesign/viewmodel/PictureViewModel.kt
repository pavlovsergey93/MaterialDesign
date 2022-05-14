package com.gmail.pavlovsv93.materialdesign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.pavlovsv93.materialdesign.data.repository.RemoteRepositoryRetrofit
import com.gmail.pavlovsv93.materialdesign.model.DataResponseNasaDTO
import com.gmail.pavlovsv93.materialdesign.utils.CORRUPTED_DATA
import com.gmail.pavlovsv93.materialdesign.utils.NASA_API_KEY
import com.gmail.pavlovsv93.materialdesign.utils.PROJECT_ERROR
import com.gmail.pavlovsv93.materialdesign.utils.SERVER_REQUEST_ERROR
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PictureViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val retrofit: RemoteRepositoryRetrofit = RemoteRepositoryRetrofit()
) : ViewModel() {

    // получение liveData
    fun getLiveData(): LiveData<AppState> = liveData

    // послать серверный запрос
    fun sendServerRequestToDate(progress: Int? = null, date: String) {
        liveData.postValue(AppState.OnLoading(progress))
        retrofit.getRetrofitImpl().getPictureByDate(NASA_API_KEY, (date))
            .enqueue(callBack)
    }


    private val callBack = object : Callback<DataResponseNasaDTO> {
        override fun onResponse(
            call: Call<DataResponseNasaDTO>,
            response: Response<DataResponseNasaDTO>
        ) {
            val serverResponse: DataResponseNasaDTO? = response.body()
            liveData.postValue(
                if (response.isSuccessful) {
                    checkDataResponse(serverResponse)
                } else {
                    AppState.OnError(Throwable(SERVER_REQUEST_ERROR.toString()))
                }
            )
        }

        override fun onFailure(call: Call<DataResponseNasaDTO>, t: Throwable) {
            AppState.OnError(Throwable(PROJECT_ERROR.toString()))
        }
    }

    private fun checkDataResponse(serverResponse: DataResponseNasaDTO?): AppState {

        return if (serverResponse == null) {
            AppState.OnError(Throwable(CORRUPTED_DATA.toString()))
        } else {
            AppState.OnSuccess(serverResponse)
        }
    }
}