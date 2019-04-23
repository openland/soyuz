package com.openland.soyuz.parser

const val OPERANDS = "{}():[]"

const val IGNORED_CHARACTERS = "\n\t, "

const val DELIMITERS = OPERANDS + IGNORED_CHARACTERS

fun tokenizeDocument(input : String) : MutableList<String> {
    var i = 0
    val tokens : MutableList<String> = mutableListOf()

    while(i < input.length){
        when(input[i]){
            in IGNORED_CHARACTERS -> { i++ }
            in OPERANDS -> {
                tokens.add(input[i].toString())
                i++
            }
            '\"' -> {
                val substring = input.substring(i + 1)
                val token = extractValueToken(substring)
                i += token.length + 2 //2 for quotes
                tokens.add("\"$token\"")
            }
            else -> {
                val tokenBuilder = StringBuilder()

                while(i < input.length && input[i] !in DELIMITERS){
                    if(input[i] !in IGNORED_CHARACTERS) tokenBuilder.append(input[i])
                    i++
                }

                if(tokenBuilder.isNotBlank()) tokens.add(tokenBuilder.toString())
            }
        }
    }

    return tokens
}

private fun extractValueToken(substring: String): String {
    var index = 0
    var isEscaped = false

    val tokenBuilder = StringBuilder()

    loop@ while (index < substring.length) {
        val currentChar = substring[index]

        isEscaped = when {
            currentChar == '\"' && isEscaped.not() -> break@loop
            currentChar == '\\' -> true
            else -> false
        }

        tokenBuilder.append(currentChar)

        index++
    }

    return tokenBuilder.toString()
}