package md.lhv.verification.core

import md.lhv.verification.core.model.MatchResult

/**
 * Checks against the sanctions list
 */
interface ScreeningService {
    fun checkName(name: String): MatchResult

}