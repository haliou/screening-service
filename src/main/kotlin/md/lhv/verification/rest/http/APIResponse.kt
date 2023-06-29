package md.lhv.verification.rest.http


/**
 * The HTTP response to a request
 */
sealed interface APIResponse

/**
 * Command succeeded
 */
class SuccessResponse(val success: Boolean) : APIResponse

/**
 * Request failed with the failure message
 */
class FailureResponse(
    val message: String = "FAILURE",
    val description: String,
) : APIResponse

/**
 * A generic error occurred
 */
class ErrorResponse(
    val message: String = "FAILURE",
    val description: String
) : APIResponse