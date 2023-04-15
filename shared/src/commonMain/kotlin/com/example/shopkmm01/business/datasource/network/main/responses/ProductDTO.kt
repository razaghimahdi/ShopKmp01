package com.example.shopkmm01.business.datasource.network.main.responses

import com.example.shopkmm01.business.domain.main.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    @SerialName("id") val id : Int,
    @SerialName("title") val title : String,
    @SerialName("price") val price : Double,
    @SerialName("description") val description : String,
    @SerialName("category") val category : String,
    @SerialName("image") val image : String,
    @SerialName("rating") val rating : RatingDTO
)

fun ProductDTO.toProduct(): Product {
    return Product(
        id = id ,
        title = title ,
        price = price ,
        description = description ,
        category = category ,
        image = image ,
        rating = rating.toRating() ,
    )
}
