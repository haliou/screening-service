package md.lhv.verification.core

import org.springframework.stereotype.Service

@Service
interface ScreeningRepository {

    fun findAll(): List<String>
    fun add(name: String): Boolean
    fun update(oldName: String, newName: String): Boolean
    fun delete(name: String): Boolean

}