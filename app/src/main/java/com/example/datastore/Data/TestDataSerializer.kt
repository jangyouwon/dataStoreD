package com.example.datastore.Data

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.StringFormat
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class TestDataSerializer(
    private val stringFormat: StringFormat = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    },
) : Serializer<TestData> {
    override val defaultValue: TestData
        get() = Singleton.TestData!!

    override suspend fun readFrom(input: InputStream): TestData {
        try {
            val bytes = input.readBytes()
            val string = bytes.decodeToString()
            return stringFormat.decodeFromString(string)
        } catch (e: SerializationException) {
            throw CorruptionException("Cannot read stored data", e)
        }
    }

    override suspend fun writeTo(t: TestData, output: OutputStream) {
        val string = stringFormat.encodeToString(t)
        val bytes = string.encodeToByteArray()
        output.write(bytes)
    }
}
