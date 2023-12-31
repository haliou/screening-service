package md.lhv.screening.service

import md.lhv.screening.core.ScreeningService
import md.lhv.screening.core.model.MatchResult
import md.lhv.screening.repository.ScreeningRepository
import md.lhv.screening.repository.db.SanctionedPerson
import org.springframework.stereotype.Service

@Service("ScreeningService")
class ScreeningServiceImpl(private val screeningRepository: ScreeningRepository) : ScreeningService {

    override fun checkName(name: String): MatchResult {

        val nameParts = name.split("\\s".toRegex())

        // Get all the names in lowercase format
        val sanctionNamesList = screeningRepository.findAll().flatMap { it.name.lowercase().split("\\s".toRegex()) }

        //TODO(Remove punctuations)
        val match = nameParts.any {
            it.lowercase() in sanctionNamesList
        }

        //TODO(Use fuzzy string matching algorithms like the Levenshtein distance for a more robust matching)

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
        return try {
            val existingEntry = screeningRepository.findByName(oldName)
            existingEntry?.let {
                it.name = newName
                screeningRepository.save(it)
            }
            true

        } catch (ex: Exception) {
            // Log exception
            false
        }
    }

    override fun removeSanctionedName(name: String): Boolean {
        return try {
            val existingEntry = screeningRepository.findByName(name)
            existingEntry?.let {
                screeningRepository.delete(it)
            }
            true
        } catch (ex: Exception) {
            // Log exception
            false
        }
    }


}