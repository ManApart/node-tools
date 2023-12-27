suspend fun base64ToImage(args: List<String>) {
    val input = args.firstOrNull() ?: throw Exception("Must provide path to json")
    println("reading $input")
    val imageData = JSON.parse<dynamic>(readFile(input))
//    println(imageData.data)

    val buffer = Buffer.from(imageData.data, "base64")
    fs.writeFileSync("dalle/output.jpeg", buffer)
}