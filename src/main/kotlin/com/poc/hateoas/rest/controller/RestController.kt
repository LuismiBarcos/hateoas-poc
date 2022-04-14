package com.poc.hateoas.rest.controller

import com.poc.hateoas.data.repository.contracts.ListRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController(private val listsRepository: ListRepository) {
    @GetMapping("/")
    fun greeting(): String = "Hello friend. Try with lists endpoint (/list)"

    @GetMapping("/lists")
    fun getAllLists(): List<com.poc.hateoas.data.model.List> = listsRepository.getLists()

    @GetMapping("/lists/{id}")
    fun getAllLists(@PathVariable id: String): com.poc.hateoas.data.model.List = listsRepository.getList(id)

    @PostMapping("/lists/{listName}")
    fun createList(@PathVariable listName: String) = listsRepository.createList(listName)

    @DeleteMapping("/lists/{id}")
    fun deleteList(@PathVariable id: String) = listsRepository.deleteList(id)
}