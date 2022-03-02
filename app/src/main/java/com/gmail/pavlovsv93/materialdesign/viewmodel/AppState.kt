package com.gmail.pavlovsv93.materialdesign.viewmodel

import com.gmail.pavlovsv93.materialdesign.model.DataResponseNasaDTO

sealed class AppState {

    data class OnSuccess(val responseData: DataResponseNasaDTO) : AppState()
    data class OnError(val error: Throwable) : AppState()
    data class OnLoading(val progress: Int?) : AppState()
}
