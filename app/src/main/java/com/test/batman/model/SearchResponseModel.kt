package com.test.batman.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SearchResponseModel(@SerializedName("Search")
                                var searchItemList: List<SearchItemModel>,
                                var totalResults: String,
                                var Response: Boolean): Serializable