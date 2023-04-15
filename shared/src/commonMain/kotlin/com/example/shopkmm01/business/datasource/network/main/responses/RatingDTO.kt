package com.example.shopkmm01.business.datasource.network.main.responses

import com.example.shopkmm01.business.domain.main.Product
import com.example.shopkmm01.business.domain.main.Rating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RatingDTO(
    @SerialName("rate") val rate: Double,
    @SerialName("count") val count: Int
)

fun RatingDTO.toRating(): Rating {
    return Rating(
        rate = rate,
        count = count,
    )
}