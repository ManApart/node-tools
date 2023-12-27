import dalle.dalle

enum class Tool(val main: (args: List<String>) -> Unit) {
    DALLE(::dalle),
    BASE64IMAGE(::base64ToImage)
}