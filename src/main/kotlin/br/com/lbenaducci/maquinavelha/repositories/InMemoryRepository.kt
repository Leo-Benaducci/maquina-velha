package br.com.lbenaducci.maquinavelha.repositories

import br.com.lbenaducci.maquinavelha.models.entities.AbstractEntity
import java.util.*

abstract class InMemoryRepository<T : AbstractEntity> {
    private val items = mutableMapOf<UUID, T>()

    fun save(item: T): T {
        items[item.id] = item
        return item
    }

    fun findById(id: UUID): T? {
        return items[id]
    }

    protected fun findByFilter(filter: (T) -> Boolean): List<T> {
        return items.values.filter(filter).toList()
    }

    fun findAll(): List<T> {
        return items.values.toList()
    }

    fun deleteById(id: UUID) {
        items.remove(id)
    }

    fun delete(item: T) {
        deleteById(item.id)
    }
}