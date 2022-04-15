package com.poc.hateoas.rest.controller

import com.poc.hateoas.data.model.ListItem
import com.poc.hateoas.data.repository.contracts.ListRepository
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.LinkRelation
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestController(private val listsRepository: ListRepository) {
    @GetMapping("/")
    fun greeting(): String {
        val lists = arrayListOf(
            com.poc.hateoas.data.model.List("Test1", "1"),
            com.poc.hateoas.data.model.List("Test2", "2"),
            com.poc.hateoas.data.model.List("Test3", "3")
        )

        lists.forEach { listsRepository.createList(it) }

        listsRepository.addListItem("1",ListItem(content = "Content 1"))
        listsRepository.addListItem("1",ListItem(content = "Content 2"))
        listsRepository.addListItem("2",ListItem(content = "Content 3"))
        listsRepository.addListItem("2",ListItem(content = "Content 4"))
        listsRepository.addListItem("3",ListItem(content = "Content 5"))
        listsRepository.addListItem("3",ListItem(content = "Content 6"))

        return "Hello friend. I've create some data. Enjoy :)"
    }

    @GetMapping("/lists")
    fun getAllLists(): ResponseEntity<CollectionModel<com.poc.hateoas.data.model.List>> {
        val lists = listsRepository.getLists()

        val restControllerClass = com.poc.hateoas.rest.controller.RestController::class.java
        lists.forEach {
            it.add(linkTo(methodOn(restControllerClass).getList(it.id)).withSelfRel())
            it.add(linkTo(methodOn(restControllerClass).getListListItems(it.id)).withRel("listItems"))
        }
        val allListLink = linkTo(methodOn(restControllerClass).getAllLists()).withSelfRel()
        return ResponseEntity.ok(CollectionModel.of(lists, allListLink))
    }

    @GetMapping("/lists/{id}")
    fun getList(@PathVariable id: String): ResponseEntity<com.poc.hateoas.data.model.List> =
        ResponseEntity.ok(listsRepository.getList(id))

    @GetMapping("/lists/{id}/list-items")
    fun getListListItems(@PathVariable id: String): ResponseEntity<List<ListItem>> =
        ResponseEntity.ok(listsRepository.getListListItem(id))

    // Very ugly POST method. This should never be done like this but with the content in the requestBody. Done like this just for demo purpose
    @PostMapping("/lists/{id}/list-items/{content}")
    fun createListItem(@PathVariable id: String, @PathVariable content: String) {
        listsRepository.addListItem(id, ListItem(content = content))
    }

    @PostMapping("/lists/{listName}")
    fun createList(@PathVariable listName: String) = listsRepository.createList(listName)

    @DeleteMapping("/lists/{id}")
    fun deleteList(@PathVariable id: String) = listsRepository.deleteList(id)
}