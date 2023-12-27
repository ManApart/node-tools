import org.w3c.files.File
import kotlin.js.Promise

@JsModule("fs")
@JsNonModule
external object fs {
    fun readFileSync(path: String, encoding: String): String
    fun existsSync(path: String): Boolean
    fun createWriteStream(fileName: String): WriteStream
}

@JsModule("fs")
@JsNonModule
external class WriteStream {
    fun write(content: String)
    fun end()
}
external fun import(module: String): Promise<dynamic>


external object Bimg {
    fun generateImageFiles(prompt: String): Array<File>
}

