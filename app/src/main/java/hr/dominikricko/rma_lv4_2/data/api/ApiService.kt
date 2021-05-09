package hr.dominikricko.rma_lv4_2.data.api

import hr.dominikricko.rma_lv4_2.data.model.User
import io.reactivex.Single

interface ApiService {

    fun getUsers(): Single<List<User>>

}