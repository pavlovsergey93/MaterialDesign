package com.gmail.pavlovsv93.materialdesign.repository

import androidx.fragment.app.Fragment

interface InMemoryRepositoryInterface {
    fun getFragmentListInMemory(): List<Fragment>
}