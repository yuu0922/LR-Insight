package io.github.yuu0922.lrinsight.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.github.yuu0922.lrinsight.R
import java.util.Locale.getDefault

private val flagMap = mapOf(
    "AE" to R.drawable.flag_ae,
    "AT" to R.drawable.flag_at,
    "AU" to R.drawable.flag_au,
    "CA" to R.drawable.flag_ca,
    "CN" to R.drawable.flag_cn,
    "CO" to R.drawable.flag_co,
    "DE" to R.drawable.flag_de,
    "EC" to R.drawable.flag_ec,
    "EG" to R.drawable.flag_eg,
    "ES" to R.drawable.flag_es,
    "FR" to R.drawable.flag_fr,
    "GB" to R.drawable.flag_gb,
    "GT" to R.drawable.flag_gt,
    "HK" to R.drawable.flag_hk,
    "ID" to R.drawable.flag_id,
    "IN" to R.drawable.flag_in,
    "IQ" to R.drawable.flag_iq,
    "IR" to R.drawable.flag_ir,
    "IT" to R.drawable.flag_it,
    "JO" to R.drawable.flag_jo,
    "JP" to R.drawable.flag_jp,
    "KR" to R.drawable.flag_kr,
    "KW" to R.drawable.flag_kw,
    "MO" to R.drawable.flag_mo,
    "MX" to R.drawable.flag_mx,
    "MY" to R.drawable.flag_my,
    "PH" to R.drawable.flag_ph,
    "PK" to R.drawable.flag_pk,
    "RG" to R.drawable.flag_rg,
    "RU" to R.drawable.flag_ru,
    "SA" to R.drawable.flag_sa,
    "SG" to R.drawable.flag_sg,
    "SY" to R.drawable.flag_sy,
    "TH" to R.drawable.flag_th,
    "TM" to R.drawable.flag_tm,
    "TR" to R.drawable.flag_tr,
    "TW" to R.drawable.flag_tw,
    "US" to R.drawable.flag_us,
    "VE" to R.drawable.flag_ve,
    "VN" to R.drawable.flag_vn,
    "RG" to R.drawable.flag_rg
)

@Composable
fun LRFlag(
    modifier: Modifier = Modifier,
    flag: String,
    outline: Boolean = true
) {
    val resId = flagMap[flag.uppercase(getDefault())] ?: R.drawable.flag_rg
    Box(
        modifier = modifier.aspectRatio(1.5f).then(
            if (outline) {
                Modifier
                    .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(2.dp))
                    .clip(RoundedCornerShape(2.dp))
            } else Modifier
        )
    ) {
        AsyncImage(
            model = resId,
            contentDescription = flag,
            modifier = Modifier.matchParentSize()
        )
    }
}