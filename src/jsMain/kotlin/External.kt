import org.w3c.files.File
import kotlin.js.Promise

@JsModule("fs")
@JsNonModule
external object fs {
    fun readFileSync(path: String, encoding: String): String
    fun writeFileSync(path: String, content: String)
    fun appendFileSync(path: String, content: String)
    fun writeFileSync(path: String, content: BufferInstance)
    fun existsSync(path: String): Boolean
    fun createWriteStream(fileName: String): WriteStream
    fun watchFile(fileName: String, handler: () -> Unit): WriteStream
}

@JsModule("fs")
@JsNonModule
external class WriteStream {
    fun write(content: String)
    fun end()
}
external fun import(module: String): Promise<dynamic>


external object Bimg {
    fun generateImageFiles(prompt: String): Promise<Array<dynamic>>
}

external object Buffer {
    fun from(data: String, type: String): BufferInstance
}

external class BufferInstance {
}
