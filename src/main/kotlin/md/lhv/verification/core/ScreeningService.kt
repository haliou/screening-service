package md.lhv.verification.core

import md.lhv.verification.core.model.MatchResult

/**
 * Service to perform operations against the sanctions list
 */
interface ScreeningService {

    /**
     * Checks the provided name
     *
     * @param name the full name to check against
     * @return the matching result
     */
    fun checkName(name: String): MatchResult

    /**
     * Add a new name entry into the sanctioned list
     *
     * @param name the new entry to add

     * @return true if the operation is successful, otherwise false
     */
    fun addSanctionedName(name: String): Boolean

    /**
     * Update an existing name in the sanctioned list
     *
     *  @param oldName the existing name to match against
     *  @param newName the name entry to update
     *
     *  @return true if the operation is successful, otherwise false
     */
    fun updateSanctionedName(oldName: String, newName: String): Boolean

    /**
     * Remove an existing name entry from the sanctioned list
     *
     *  @param name the name record to delete
     *
     *  @return true if the operation is successful, otherwise false
     */
    fun removeSanctionedName(name: String): Boolean

}