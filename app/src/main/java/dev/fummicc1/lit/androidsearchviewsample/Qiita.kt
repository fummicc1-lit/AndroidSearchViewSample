package dev.fummicc1.lit.androidsearchviewsample

data class Qiita(
    val renderedBody: String,
    val title: String,
    val user: User,
    val createdAt: String,
    val updatedAt: String,
    val url: String
) {
    data class User(
        val name: String
    )
}
