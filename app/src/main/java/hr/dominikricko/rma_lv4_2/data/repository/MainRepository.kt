package hr.dominikricko.rma_lv4_2.data.repository

import hr.dominikricko.rma_lv4_2.data.api.ApiHelper
import hr.dominikricko.rma_lv4_2.data.model.User
import io.reactivex.Single

class MainRepository(private val apiHelper: ApiHelper) {

    fun getUsers(): Single<List<User>> {
        return apiHelper.getUsers()
    }

}