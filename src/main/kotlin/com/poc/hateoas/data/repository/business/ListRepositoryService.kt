package com.poc.hateoas.data.repository.business

import com.poc.hateoas.data.model.ListItem
import com.poc.hateoas.data.repository.contracts.ListRepository
import org.springframework.stereotype.Component

@Component
class ListRepositoryService(
    private val lists: MutableMap<com.poc.hateoas.data.model.List, MutableList<ListItem>> = HashMap()
): ListRepository {

    override fun createList(listName: String) {
        lists[com.poc.hateoas.data.model.List(listName)] = mutableListOf()
    }

    override fun deleteList(id: String) {
        lists.remove(findList(id))
    }

    override fun getList(id: String): com.poc.hateoas.data.model.List = findList(id)!!

    override fun getLists(): List<com.poc.hateoas.data.model.List> = lists.toList().map { (list, _) -> list }

    override fun addListItem(listId: String, listItem: ListItem) {
        val list = findList(listId)
        val listItems = lists[list]
        lists[list!!] = listItems!!
    }

    private fun findList(listId: String): com.poc.hateoas.data.model.List? =
        lists.toList().find { (list, _) -> list.id == listId}?.first

}