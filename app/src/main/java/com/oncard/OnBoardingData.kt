package com.oncard

import androidx.compose.ui.graphics.Color
import com.oncard.ui.theme.ColorBlue

data class OnBoardingData(
    val image:Int,
    val backgroundColor: Color = ColorBlue,
    val mainColor: Color = ColorBlue,
    val mainText:String,
    val subText:String
)
