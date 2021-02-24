package dev.fummicc1.lit.androidsearchviewsample

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface QiitaClientInterface {
    suspend fun getItems(query: String, page: Int, countPerPage: Int): List<Qiita>
}

class QiitaClient : QiitaClientInterface {
    private val baseUrl: String = "https://qiita.com/api/v2/"

    private val gson: Gson

    private val qiitaService: QiitaService

    init {
        gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        qiitaService = retrofit.create(QiitaService::class.java)
    }

    override suspend fun getItems(query: String, page: Int, countPerPage: Int): List<Qiita> =
        qiitaService.getItems(page, countPerPage, query)
}