package org.example.util

import java.io.FileNotFoundException
import java.io.FileReader
import java.io.IOException
import java.util.*

object ReadProperties {
    private var PROPERTIES: Properties? = null

    @Throws(IOException::class)
    operator fun get(key: String?): String {
        try {
            FileReader("src/test/resources/test.properties").use { fileReader ->
                PROPERTIES = Properties()
                PROPERTIES!!.load(fileReader)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
        return PROPERTIES!!.getProperty(key)
    }
}