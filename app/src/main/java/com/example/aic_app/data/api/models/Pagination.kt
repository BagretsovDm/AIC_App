package com.example.aic_app.data.api.models

import com.squareup.moshi.Json

data class Pagination(
    @field:Json(name = "current_page")
    val currentPage: Long,
    @field:Json(name = "total_pages")
    val nextUrl: Long
)
