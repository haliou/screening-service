package md.lhv.verification.core.model

/**
 * Represent the result of a check against the sanctions list
 */
data class MatchResult(val isMatch: Boolean, val extraData: Any? = null)