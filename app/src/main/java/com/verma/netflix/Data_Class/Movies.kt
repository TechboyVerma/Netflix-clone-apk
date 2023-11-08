package com.verma.netflix.Models

import com.verma.netflix.Models.Result

data class Movies(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)
