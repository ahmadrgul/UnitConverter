package com.example.unitconverter.core.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

class AndroidClipboardService(
    private val context: Context
) : ClipboardService {
    override fun copyToClipboard(text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Conversion Result", text)
        clipboard.setPrimaryClip(clip)
    }
}