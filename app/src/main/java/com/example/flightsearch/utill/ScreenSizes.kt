package com.example.flightsearch.utill

import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass

enum class ScreenSizes {
    MOBILE_PORTRAIT,
    MOBILE_LANDSCAPE;

    companion object {
        fun getCurrentDeviceScreenSize(windowSizeClass: WindowSizeClass): ScreenSizes {
            return when {
                windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT ||
                        windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM -> MOBILE_PORTRAIT

                else -> MOBILE_LANDSCAPE
            }
        }
    }
}