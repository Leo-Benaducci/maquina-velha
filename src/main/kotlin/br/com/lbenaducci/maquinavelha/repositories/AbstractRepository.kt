package br.com.lbenaducci.maquinavelha.repositories

import br.com.lbenaducci.maquinavelha.models.entities.AbstractEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface AbstractRepository<T : AbstractEntity> : JpaRepository<T, UUID>