package zelimkhan.magomadov.notes.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import zelimkhan.magomadov.notes.R

private val Montserrat = FontFamily(Font(R.font.montserrat_regular))
private val MontserratMedium = FontFamily(Font(R.font.montserrat_medium))
private val MontserratSemiBold = FontFamily(Font(R.font.montserrat_semibold))

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 36.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 24.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 20.sp,
    ),
    headlineLarge = TextStyle(
        fontFamily = MontserratSemiBold,
        fontSize = 30.sp,
    ),
    headlineMedium = TextStyle(
        fontFamily = MontserratSemiBold,
        fontSize = 24.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = MontserratSemiBold,
        fontSize = 20.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 18.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 16.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 14.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Montserrat,
        fontSize = 18.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Montserrat,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = Montserrat,
        fontSize = 14.sp,
    ),
    labelLarge = TextStyle(
        fontFamily = Montserrat,
        fontSize = 16.sp,
    ),
    labelMedium = TextStyle(
        fontFamily = Montserrat,
        fontSize = 14.sp,
    ),
    labelSmall = TextStyle(
        fontFamily = Montserrat,
        fontSize = 12.sp,
    )
)
