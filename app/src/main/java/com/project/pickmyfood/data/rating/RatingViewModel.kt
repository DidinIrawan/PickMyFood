package com.project.pickmyfood.data.rating

import javax.inject.Inject


class RatingViewModel @Inject constructor(var ratingRespository: RatingRespository) {
    var ratingResponse = ratingRespository.ratingResponse
    fun ratingResto(rating: Rating) {
        ratingRespository.ratingResto(rating)
    }
}