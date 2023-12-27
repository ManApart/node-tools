external fun require(name: String): dynamic

@JsModule("fs")
@JsNonModule
external object FS {
    fun readFileSync(path: String, encoding: String): String
    fun existsSync(path: String): Boolean
    fun createWriteStream(fileName:String): WriteStream
}

@JsModule("fs")
@JsNonModule
external class WriteStream {
    fun write(content: String)
    fun end()
}