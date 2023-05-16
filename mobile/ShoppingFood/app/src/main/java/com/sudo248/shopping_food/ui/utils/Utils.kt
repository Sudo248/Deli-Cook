package com.sudo248.shopping_food.ui.utils

import com.sudo248.base_android.core.DataState
import com.sudo248.base_android.exception.ApiException
import com.sudo248.shopping_food.domain.model.BaseResponse
import java.text.NumberFormat
import java.util.*

fun Double.format(digit: Int = 1):String {
    return String.format("%.${digit}f", this)
}

object Utils {
    private val locale = Locale("en", "US")
    fun format(value: Double, digit: Int): String {
        return value.format(digit)
    }
    fun formatCurrency(price: Double): String {
        return NumberFormat.getCurrencyInstance(locale).format(price)
    }
}

fun getErrorMessageFromResponse(response: DataState<BaseResponse<*>, ApiException>): String {
    return try {
        (response.error().data as BaseResponse<*>).error ?: response.error().message
    } catch (e: Exception) {
        response.error().message
    }
}