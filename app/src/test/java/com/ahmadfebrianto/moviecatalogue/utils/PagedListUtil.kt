package com.ahmadfebrianto.moviecatalogue.utils

import androidx.paging.PagedList
import org.mockito.Mockito.*

object PagedListUtil {
    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedlist = mock(PagedList::class.java) as PagedList<T>
        `when`(pagedlist[anyInt()]).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }

        `when`(pagedlist.size).thenReturn(list.size)

        return pagedlist
    }
}