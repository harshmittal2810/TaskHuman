package com.harsh.taskhuman.common.util.initialiser.properties

val reservedWords = setOf("accountId", "appId", "timestamp", "type", "eventType")

/**
 * Created by Harsh Mittal on 25/02/22 05:20 PM
 */

class VerifiedEventAttributes constructor(baseAttributes: Map<String, Any?>, isDebug: Boolean) {

    private val nullAttributeKeys: List<String>
    private val attributes: Map<String, Any>

    init {
        val attributesBuilder = mutableMapOf<String, Any>()
        val nullAttributesKeyBuilder = mutableListOf<String>()

        baseAttributes.entries.forEach { (key, value) ->

            val newValue = if (value == null) {
                nullAttributesKeyBuilder.add(key)
                "null"
            } else {
                value
            }

            if (reservedWords.contains(key)) {
                if (isDebug) {
                    throw IllegalArgumentException("You cannot use a reserved word as an attribute name: $key")
                }
                attributesBuilder["_$key"] = newValue
            } else {
                attributesBuilder[key] = newValue
            }
        }

        nullAttributeKeys = nullAttributesKeyBuilder.toList()
        attributes = attributesBuilder.toMap()
    }

    fun hasNullAttributes() = nullAttributeKeys.isNotEmpty()

    override fun toString(): String {
        return attributes.toString()
    }

    override fun equals(other: Any?): Boolean {
        return (other as? VerifiedEventAttributes)?.attributes?.equals(attributes) ?: false
    }

    override fun hashCode(): Int {
        return attributes.hashCode()
    }
}