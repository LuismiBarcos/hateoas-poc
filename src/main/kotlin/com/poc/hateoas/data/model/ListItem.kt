package com.poc.hateoas.data.model

import org.springframework.hateoas.RepresentationModel
import java.util.*

data class ListItem(
    val id: String = UUID.randomUUID().toString(),
    val content: String
): RepresentationModel<ListItem>()