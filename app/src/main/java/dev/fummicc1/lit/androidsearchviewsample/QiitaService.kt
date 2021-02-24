package dev.fummicc1.lit.androidsearchviewsample

import retrofit2.http.GET
import retrofit2.http.Query

interface QiitaService {
    @GET("items")
    suspend fun getItems(
        @Query("page") page: Int,
        @Query("per_page") countPerPage: Int,
        @Query("query") query: String
    ): List<Qiita>
}