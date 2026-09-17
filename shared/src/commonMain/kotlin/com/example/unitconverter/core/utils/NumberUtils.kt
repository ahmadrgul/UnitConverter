package com.example.unitconverter.core.utils

fun Double.toCleanNumberString(): String {
    return if (this % 1.0 == 0.0) "${this.toInt()}"
    else "$this"
}