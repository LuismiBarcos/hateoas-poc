package com.poc.hateoas.data.model

import java.util.UUID

data class List(
    val name: String,
    val id: String = UUID.randomUUID().toString()
)