external val process: dynamic

fun main() {
    val argv = process.argv.slice(2) as Array<String>
    val args = argv.drop(1)
    Tool.entries.firstOrNull{it.name.lowercase() == argv.firstOrNull()}?.let { it.main(args) } ?: println("No tool found for ${argv.joinToString(" ")}")
}

fun readFile(path: String) : String {
  return fs.readFileSync(path, "utf8")
}