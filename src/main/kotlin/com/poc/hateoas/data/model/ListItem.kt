package com.poc.hateoas.data.model

import java.util.*

data class ListItem(
    private val id: String = UUID.randomUUID().toString(),
    private val content: String,
    private val listId: String
)