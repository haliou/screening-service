package md.lhv.screening.rest.http

/**
 * Payload of requests sent to the server
 */
sealed class RequestPayload

/**
 * Add new entry request payload
 */
class AddRequest(val name: String, val date_of_birth: String? = null) : RequestPayload()

/**
 * Update existing entry request payload
 */
class UpdateRequest(val oldName: String, val newName: String) : RequestPayload()