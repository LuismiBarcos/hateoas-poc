package com.poc.hateoas.data.model

import org.springframework.hateoas.RepresentationModel
import java.util.UUID

data class List(
    val name: String,
    val id: String = UUID.randomUUID().toString()
) : RepresentationModel<com.poc.hateoas.data.model.List>()