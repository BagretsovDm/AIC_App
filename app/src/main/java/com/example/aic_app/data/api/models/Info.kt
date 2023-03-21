package com.example.aic_app.data.api.models

import com.squareup.moshi.Json

data class Info(
    @field:Json(name = "license_text")
    val licenseText: String,
)
