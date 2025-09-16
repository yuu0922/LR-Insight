package io.github.yuu0922.lrinsight.util

object LRUtils {
    fun formatPlayerLevel(level: Int): String {
        var prefix = ""
        when ((level - 1) / 99) {
            1 -> prefix = "M"
            2 -> prefix = "S"
            3 -> prefix = "U"
            4 -> prefix = "L"
        }

        var level = level % 99
        if (level == 0) {
            level = 99
        }

        return prefix + level.toString()
    }

    fun getUserProfileUrl(id: String): String {
        return "https://rangers.lerico.net/res/user_profile_img/${id}.png"
    }

    fun getRangerImageUrl(unitCode: String): String {
        return "https://rangers.lerico.net/res/${unitCode}/${unitCode}-thum.png"
    }

    fun getRangerPageUrl(unitCode: String): String {
//        val supportedLangs = setOf("en", "zh", "ja")
//        val currentLang = Locale.getDefault().language
//        val lang = if (supportedLangs.contains(currentLang)) {
//            currentLang
//        } else {
//            "en"
//        }
//        return "https://rangers.lerico.net/$lang/ranger/${unitCode}"
        return "https://rangers.lerico.net/ranger/$unitCode"
    }
}