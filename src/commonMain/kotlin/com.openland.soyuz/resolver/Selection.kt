package com.openland.soyuz.resolver

import com.openland.soyuz.parser.*

class Selection(val fields: List<SelectionField>)

class SelectionField(val requestKey: String, val storeKey: String, val fields: List<SelectionField>)

private fun valueKey(value: DocumentValue): String {
    return when (value) {
        is DocumentValue.Constant -> value.constant
        is DocumentValue.List -> "[" + value.values.joinToString(",") { valueKey(it) } + "]"
        else -> "REFERENCE"
    }
}

private fun argumentsKey(args: List<DocumentFieldArgument>?): String {
    if (args == null || args.isEmpty()) {
        return ""
    }
    val res = mutableListOf<String>()
    for (arg in args.sortedBy { it.name }) {
        res.add(arg.name + ":" + valueKey(arg.value))
    }
    return "(" + res.joinToString(",") + ")"
}

private fun createSelection(selectionFields: List<DocumentSelectionField>): List<SelectionField> {
    val res = mutableListOf<SelectionField>()
    for (selection in selectionFields) {
        val storeKey = selection.name + argumentsKey(selection.arguments)
        var fields: List<SelectionField> = emptyList()
        if (selection.selection != null) {
            fields = createSelection(selection.selection.fields)
        }
        res.add(
            SelectionField(
                selection.alias ?: selection.name,
                storeKey,
                fields
            )
        )
    }
    return res
}

fun createSelection(src: Document): Selection {
    var query = "ROOT_QUERY"
    if (src.type == DocumentType.MUTATION) {
        query = "ROOT_MUTATION"
    } else if (src.type == DocumentType.SUBSCRIPTION) {
        query = "ROOT_SUBSCRIPTION"
    }
    val res = mutableListOf<SelectionField>()
    for (selection in src.selection.fields) {
        val storeKey = query + "." + selection.name + argumentsKey(selection.arguments)
        var fields: List<SelectionField> = emptyList()
        if (selection.selection != null) {
            fields = createSelection(selection.selection.fields)
        }
        res.add(
            SelectionField(
                selection.alias ?: selection.name,
                storeKey,
                fields
            )
        )
    }
    return Selection(res)
}