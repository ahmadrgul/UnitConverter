package com.example.unitconverter.core.utils

import platform.UIKit.UIPasteboard

class IosClipboardService : ClipboardService {
    override fun copyToClipboard(text: String) {
        UIPasteboard.generalPasteboard.string = text
    }
}