
val fs = require("fs") as FS
//val fs = require("fs")


fun dalle(args: List<String>) {
    console.log("Hello, Kotlin/JS!")
    val input = fs.readFileSync("dalle/input.txt", "utf8")
    println(input)
    val stream = fs.createWriteStream("dalle/output.txt")
    stream.write("test")
    stream.end()
}