package com.poc.hateoas.data.repository.contracts

import com.poc.hateoas.data.model.ListItem

interface ListRepository{
    fun createList(listName: String)
    fun createList(list: com.poc.hateoas.data.model.List)
    fun deleteList(id: String)
    fun getList(id: String): com.poc.hateoas.data.model.List?
    fun getLists(): List<com.poc.hateoas.data.model.List>
    fun addListItem(listId: String, listItem: ListItem)
    fun getListListItem(listId: String): List<ListItem>?
}