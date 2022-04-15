package com.gmail.pavlovsv93.materialdesign.domain

import androidx.fragment.app.Fragment

interface InMemoryRepositoryInterface {
    fun getFragmentListInMemory(): List<Fragment>
}