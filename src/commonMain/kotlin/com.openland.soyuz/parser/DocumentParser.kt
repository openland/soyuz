package com.openland.soyuz.parser

class TokenReader(private val tokens: MutableList<String>) {
    fun read(): String {
        val res = tokens[0]
        tokens.removeAt(0)
        return res
    }

    fun peek(): String {
        return tokens[0]
    }

    fun assert(token: String) {
        val r = read()
        if (r != token) {
            throw Error("Mailformed query. Expected: $token, got $r")
        }
    }
}

private fun parseSelectionSet(tokens: TokenReader): DocumentSelection {
    val fields = mutableListOf<DocumentSelectionField>()
    while (tokens.peek() != "}") {
        var name = tokens.read()
        var alias: String? = null
        if (tokens.peek() == ":") {
            tokens.read()
            alias = name
            name = tokens.read()
        }
        if (tokens.peek() == "(") {
            parseArguments(tokens)
            tokens.assert(")")
        }
        if (tokens.peek() == "{") {
            tokens.assert("{")
            val res = parseSelectionSet(tokens)
            fields.add(DocumentSelectionField(name, alias, res, null))
            tokens.assert("}")
        } else {
            fields.add(DocumentSelectionField(name, alias, null, null))
        }
    }
    return DocumentSelection(fields)
}

private fun parseType(tokens: TokenReader): DocumentArgumentType {
    var name = tokens.read()
    var res: DocumentArgumentType
    if (name == "[") {
        res = DocumentArgumentType.List(parseType(tokens))
        tokens.assert("]")
    } else {
        var notNullable = false
        if (name.endsWith("!")) {
            name = name.substring(0, name.length - 1)
            notNullable = true
        }
        if (name == "String") {
            res = DocumentArgumentType.String
        } else if (name == "Int") {
            res = DocumentArgumentType.Int
        } else if (name == "Float") {
            res = DocumentArgumentType.Float
        } else if (name == "Boolean") {
            res = DocumentArgumentType.Boolean
        } else {
            res = DocumentArgumentType.Named(name)
        }
        if (notNullable) {
            res = DocumentArgumentType.NotNull(res)
        }
    }
    if (tokens.peek() == "!") {
        tokens.read()
        return DocumentArgumentType.NotNull(res)
    }
    return res
}

private fun parseArgumentValue(tokens: TokenReader) {

}

private fun parseArguments(tokens: TokenReader): List<DocumentArgument> {
    val arguments = mutableListOf<DocumentArgument>()
    while (tokens.peek() != ")") {
        val name = tokens.read()
        if (!name.startsWith("$")) {
            throw Error("Mailformed query $name")
        }
        tokens.assert(":")
        val type = parseType(tokens)
        arguments.add(DocumentArgument(name, type))
        if (tokens.peek() == "=") {
            parseArgumentValue(tokens)
        }
    }
    return arguments
}

private fun parseRoot(tokens: TokenReader): Document {
    var t = tokens.read()
    if (t == "query" || t == "mutation" || t == "subscription") {
        val documentType = when (t) {
            "mutation" -> DocumentType.MUTATION
            "subscription" -> DocumentType.SUBSCRIPTION
            else -> DocumentType.QUERY
        }
        val selection: DocumentSelection
        var arguments: List<DocumentArgument>? = null
        t = tokens.read()
        var name: String? = null
        if (t != "{" && t != "(") {
            name = t
            t = tokens.read()
        }
        if (t == "{") {
            selection = parseSelectionSet(tokens)
            tokens.assert("}")
        } else if (t == "(") {
            arguments = parseArguments(tokens)
            tokens.assert(")")
            tokens.assert("{")
            selection = parseSelectionSet(tokens)
            tokens.assert("}")
        } else {
            throw Error("Mailformed request $t")
        }
        return Document(documentType, name, arguments, selection)
    } else if (t == "{") {
        val selection = parseSelectionSet(tokens)
        tokens.assert("}")
        return Document(DocumentType.QUERY, null, null, selection)
    } else {
        throw Error("Mailformed request")
    }
}

fun parseDocument(src: String): Document {
    val tokens = tokenizeDocument(src)
    val reader = TokenReader(tokens)
    return parseRoot(reader)
}