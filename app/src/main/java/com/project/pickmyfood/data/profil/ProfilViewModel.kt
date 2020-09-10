package com.project.pickmyfood.data.profil

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class ProfilViewModel @Inject constructor(var profilRepository: ProfilRepository) {
    val profil: MutableLiveData<KeyProfil>? = profilRepository.profilUser

    fun getUserProfil(id: String) {
        println("masuk view model get by id")
        profilRepository.getUserProfil(id)
    }
}