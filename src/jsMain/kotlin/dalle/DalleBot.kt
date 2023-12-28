package dalle

import Bimg
import FakeSync
import fs
import import
import kotlinx.coroutines.delay
import process
import readFile
import kotlin.js.Date
import kotlin.js.Promise

const val dryrun = false
var readFile = true

suspend fun dalle(args: List<String>) {
    val config = JSON.parse<dynamic>(readFile("config.json"))
    process.env["BING_IMAGE_COOKIE"] = config.dalleCookie
    val bimg = FakeSync.promise { import("bimg") as Promise<Bimg> }!!

    fs.watchFile("dalle/input.txt") {
        val input = fs.readFileSync("dalle/input.txt", "utf8").split("\n").filter { it.isNotBlank() }
        if (input.isNotEmpty()) readFile = true
    }

    generateAllImages(bimg)
}

private suspend fun generateAllImages(bimg: Bimg) {
    if (readFile) {
        readFile = false
        val input = fs.readFileSync("dalle/input.txt", "utf8").split("\n").filter { it.isNotBlank() }
        println("Processing ${input.size} prompts")
        input.forEach { prompt ->
            val promptId = Date().toJSON().toString()
            try {
                generateImages(bimg, promptId, prompt)
            } catch (e: Exception) {
                println("Failed to generate images for: $prompt")
            }
        }
        cleanupInput(input)
        println("Processing complete")
    }
    delay(1000)
    generateAllImages(bimg)
}

private suspend fun generateImages(bimg: Bimg, promptId: String, prompt: String) {
    FakeSync.promise {
        if (dryrun) fakeGen(prompt) else bimg.generateImageFiles(prompt)
    }?.take(4)?.forEachIndexed { i, file ->
        val id = "$promptId-$i"
        console.log("saving $id")
        val data = Buffer.from(file.data, "base64")
        fs.writeFileSync("dalle/$id.jpeg", data)
    }

    fs.appendFileSync("dalle/output.txt", "$promptId: $prompt\n")
}

private fun cleanupInput(prompts: List<String>) {
    val newInput = fs.readFileSync("dalle/input.txt", "utf8").split("\n").filter { it.isNotBlank() }
    val cleaned = newInput.filter { !prompts.contains(it) }
    if (newInput.isNotEmpty()) {
        val stream = fs.createWriteStream("dalle/input.txt")
        stream.write(cleaned.joinToString("\n"))
        stream.end()
    }
}


private fun fakeGen(prompt: String): Promise<Array<dynamic>> {
    return Promise.resolve(emptyArray())
}