package com.kjh.umc_project9.model

import com.google.gson.annotations.SerializedName

data class CookRecipe(
    @SerializedName("COOKRCP01") val cookRecipe: Total
) {
    override fun toString(): String {
        return """
            |$cookRecipe
        """.trimMargin()
    }
}

data class Total(
    @SerializedName("total_count") val totalCount: String,
    @SerializedName("row") val recipe: List<Recipe>,
    @SerializedName("RESULT") val result: RESULT,
) {
    override fun toString(): String {
        return """
            |totalCount : $totalCount
            |result : $result
            |$recipe
        """.trimMargin()
    }
}

data class RESULT(
    @SerializedName("CODE") val code: String,
    @SerializedName("MSG") val msg: String,
) {
    override fun toString(): String {
        return """
            |(code -> $code, msg -> $msg)
        """.trimMargin()
    }
}

data class Recipe(
    @SerializedName("RCP_PARTS_DTLS") val recipeDetail: String,
    @SerializedName("RCP_WAY2") val recipeWay: String,
    @SerializedName("ATT_FILE_NO_MAIN") val picture: String,
    @SerializedName("ATT_FILE_NO_MK") val picture2: String,
    @SerializedName("HASH_TAG") val hashTag: String,
    @SerializedName("INFO_CAR") val infoCar: String,
    @SerializedName("INFO_ENG") val infoEng: String,
    @SerializedName("INFO_FAT") val infoFat: String,
    @SerializedName("INFO_NA") val infoNa: String,
    @SerializedName("INFO_PRO") val infoPro: String,
    @SerializedName("INFO_WGT") val infoWgt: String,
    @SerializedName("MANUAL01") val manual01: String,
    @SerializedName("MANUAL02") val manual02: String,
    @SerializedName("MANUAL03") val manual03: String,
    @SerializedName("MANUAL04") val manual04: String,
    @SerializedName("MANUAL05") val manual05: String,
    @SerializedName("MANUAL06") val manual06: String,
    @SerializedName("MANUAL07") val manual07: String,
    @SerializedName("MANUAL08") val manual08: String,
    @SerializedName("MANUAL09") val manual09: String,
    @SerializedName("MANUAL10") val manual10: String,
    @SerializedName("MANUAL11") val manual11: String,
    @SerializedName("MANUAL12") val manual12: String,
    @SerializedName("MANUAL13") val manual13: String,
    @SerializedName("MANUAL14") val manual14: String,
    @SerializedName("MANUAL15") val manual15: String,
    @SerializedName("MANUAL16") val manual16: String,
    @SerializedName("MANUAL17") val manual17: String,
    @SerializedName("MANUAL18") val manual18: String,
    @SerializedName("MANUAL19") val manual19: String,
    @SerializedName("MANUAL20") val manual20: String,
    @SerializedName("MANUAL_IMG01") val manualImg01: String,
    @SerializedName("MANUAL_IMG02") val manualImg02: String,
    @SerializedName("MANUAL_IMG03") val manualImg03: String,
    @SerializedName("MANUAL_IMG04") val manualImg04: String,
    @SerializedName("MANUAL_IMG05") val manualImg05: String,
    @SerializedName("MANUAL_IMG06") val manualImg06: String,
    @SerializedName("MANUAL_IMG07") val manualImg07: String,
    @SerializedName("MANUAL_IMG08") val manualImg08: String,
    @SerializedName("MANUAL_IMG09") val manualImg09: String,
    @SerializedName("MANUAL_IMG10") val manualImg10: String,
    @SerializedName("MANUAL_IMG11") val manualImg11: String,
    @SerializedName("MANUAL_IMG12") val manualImg12: String,
    @SerializedName("MANUAL_IMG13") val manualImg13: String,
    @SerializedName("MANUAL_IMG14") val manualImg14: String,
    @SerializedName("MANUAL_IMG15") val manualImg15: String,
    @SerializedName("MANUAL_IMG16") val manualImg16: String,
    @SerializedName("MANUAL_IMG17") val manualImg17: String,
    @SerializedName("MANUAL_IMG18") val manualImg18: String,
    @SerializedName("MANUAL_IMG19") val manualImg19: String,
    @SerializedName("MANUAL_IMG20") val manualImg20: String,
    @SerializedName("RCP_NA_TIP") val recipeTip: String,
    @SerializedName("RCP_NM") val name: String,
    @SerializedName("RCP_PAT2") val recipePat: String,
    @SerializedName("RCP_SEQ") val recipeSeq: String,
) {
    override fun toString(): String {
        return """
            |recipeDetail : $recipeDetail
            |recipeWay : $recipeWay
            |picture : $picture
            |picture2 : $picture2
            |hashTag : $hashTag
            |infoCar : $infoCar
            |infoEng : $infoEng
            |infoFat : $infoFat
            |infoNa : $infoNa
            |infoPro : $infoPro
            |manual01 : $manual01
            |manual02 : $manual02
            |manual03 : $manual03
            |manualImg01 : $manualImg01
            |manualImg02 : $manualImg02
            |manualImg03 : $manualImg03
            |recipeTip : $recipeTip
            |name : $name
            |recipePat : $recipePat
            |recipeSeq : $recipeSeq
        """.trimMargin()
    }
}