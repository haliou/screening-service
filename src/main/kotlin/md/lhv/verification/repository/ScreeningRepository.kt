package md.lhv.verification.repository

import md.lhv.verification.repository.db.SanctionedPerson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScreeningRepository : JpaRepository<SanctionedPerson, Long> {

    fun findByName(name: String): SanctionedPerson
}