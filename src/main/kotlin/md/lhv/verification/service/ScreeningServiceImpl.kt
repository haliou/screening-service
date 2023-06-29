package md.lhv.verification.service

import md.lhv.verification.core.ScreeningService
import md.lhv.verification.core.model.MatchResult
import md.lhv.verification.repository.ScreeningRepository
import md.lhv.verification.repository.db.SanctionedPerson
import org.springframework.stereotype.Service

@Service("ScreeningService")
class ScreeningServiceImpl(private val screeningRepository: ScreeningRepository) : ScreeningService {

    override fun checkName(name: String): MatchResult {

        val nameParts = name.split("\\s".toRegex())
        val sanctionNamesList = screeningRepository.findAll().flatMap { it.name.split("\\s".toRegex()) }

        val match = nameParts.any {
            it in sanctionNamesList
        }

        return MatchResult(isMatch = match)
    }

    override fun addSanctionedName(name: String): Boolean {
        val sanctionedPerson = SanctionedPerson(name = name)
        return try {
            screeningRepository.save(sanctionedPerson)
            true
        } catch (ex: Exception) {
            // Log exception
            false
        }
    }

    override fun updateSanctionedName(oldName: String, newName: String): Boolean {
        val existingEntry = screeningRepository.findByName(oldName)
        existingEntry.name = newName
        return try {
            screeningRepository.save(existingEntry)
            true
        } catch (ex: Exception) {
            // Log exception
            false
        }
    }

    override fun removeSanctionedName(name: String): Boolean {
        val existingEntry = screeningRepository.findByName(name)
        return try {
            screeningRepository.delete(existingEntry)
            true
        } catch (ex: Exception) {
            // Log exception
            false
        }
    }
}