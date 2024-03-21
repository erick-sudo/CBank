package com.phanes.phanesbank.ui.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GroupListItem(
    @Json(name = "name") val name: String,
    @Json(name = "number_of_members") val numberOfMembers: Int,
    @Json(name = "credit_score") val creditScore: Float,
)