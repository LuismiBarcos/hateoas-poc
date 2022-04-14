package com.poc.hateoas.data.repository.contracts

import com.poc.hateoas.data.model.ListItem

interface ListItemRepository {
    fun createListItem(listId: String, content: String)
    fun deleteListItem(id: String)
    fun getListItem(id: String): ListItem
}