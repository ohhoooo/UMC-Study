package com.kjh.umc_project9.model

import com.google.gson.annotations.SerializedName

data class Ministry(
    @SerializedName("currentCount") val currentCount: Int,
    @SerializedName("data") val data: List<MinistryBody>,
    @SerializedName("matchCount") val matchCount: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("perPage")val perPage: Int,
    @SerializedName("totalCount") val totalCount: Int
) {
    override fun toString(): String {
        return """
            |currentCount : $currentCount
            |matchCount : $matchCount
            |page : $page
            |perPage : $perPage
            |totalCount : $totalCount
            |$data
        """.trimMargin()
    }
}

data class MinistryBody(
    @SerializedName("대표전화번호") val number: String,
    @SerializedName("도서지역여부") val isIslandStatus: String,
    @SerializedName("보건기관명") val name: String,
    @SerializedName("보건기관 유형") val type: String,
    @SerializedName("시군구") val city: String,
    @SerializedName("시도") val province: String,
    @SerializedName("우편번호") val postalCode: String,
    @SerializedName("읍면동명") val town: String,
    @SerializedName("주소") val address: String
) {
    override fun toString(): String {
        return """
            |number : $number
            |isIslandStatus : $isIslandStatus
            |name : $name
            |type : $type
            |city : $city
            |province : $province
            |postalCode : $postalCode
            |town : $town
            |address : $address
        """.trimMargin()
    }
}