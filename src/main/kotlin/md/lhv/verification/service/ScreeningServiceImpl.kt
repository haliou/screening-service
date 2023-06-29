package md.lhv.verification.service

import md.lhv.verification.core.ScreeningRepository
import md.lhv.verification.core.ScreeningService
import md.lhv.verification.core.model.MatchResult
import org.springframework.stereotype.Service

@Service
class ScreeningServiceImpl(private val screeningRepository: ScreeningRepository) : ScreeningService {

    override fun checkName(name: String): MatchResult {

        val nameParts = name.split("\\s".toRegex())
        val sanctionNamesList = screeningRepository.findAll().flatMap { it.split("\\s".toRegex()) }

        val match = nameParts.any {
            it in sanctionNamesList
        }

        return MatchResult(isMatch = match)
    }

    override fun addSanctionedName(name: String): Boolean {
        return screeningRepository.add(name)
    }

    override fun updateSanctionedName(oldName: String, newName: String): Boolean {
        return screeningRepository.update(oldName = oldName, newName = newName)
    }

    override fun removeSanctionedName(name: String): Boolean {
        return screeningRepository.delete(name)
    }
}