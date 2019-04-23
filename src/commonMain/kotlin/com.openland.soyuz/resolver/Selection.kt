package com.openland.soyuz.resolver

class Selection(val fields: Array<SelectionField>)

class SelectionField(val requestKey: String, val storeKey: String, val fields: Array<SelectionField>)