package com.openland.soyuz.parser

enum class DocumentType {
    QUERY, MUTATION, SUBSCRIPTION
}

sealed class DocumentValue {
    data class Reference(val name: String) : DocumentValue()
    data class List(val values: kotlin.collections.List<DocumentValue>) : DocumentValue()
    data class Constant(val constant: String) : DocumentValue()
}

sealed class DocumentArgumentType {
    object Int : DocumentArgumentType()
    object Float : DocumentArgumentType()
    object String : DocumentArgumentType()
    object Boolean : DocumentArgumentType()
    data class Named(val name: kotlin.String) : DocumentArgumentType()
    data class NotNull(val inner: DocumentArgumentType) : DocumentArgumentType()
    data class List(val inner: DocumentArgumentType) : DocumentArgumentType()
}

class DocumentFieldArgument(val name: String, val value: DocumentValue)

class DocumentArgument(val name: String, val type: DocumentArgumentType)

data class DocumentSelectionField(
    val name: String,
    val alias: String?,
    val selection: DocumentSelection?,
    val arguments: List<DocumentFieldArgument>?
)

data class DocumentSelection(val fields: List<DocumentSelectionField>)

data class Document(
    val type: DocumentType,
    val name: String?,
    val arguments: List<DocumentArgument>,
    val selection: DocumentSelection
)