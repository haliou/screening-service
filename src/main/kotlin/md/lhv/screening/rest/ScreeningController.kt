package md.lhv.screening.rest

import md.lhv.screening.core.ScreeningService
import md.lhv.screening.core.model.MatchResult
import md.lhv.screening.rest.http.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/screening", produces = [MediaType.APPLICATION_JSON_VALUE])
class ScreeningController {

    private var logger: Logger = LoggerFactory.getLogger(ScreeningController::class.java)


    @Autowired
    private lateinit var screeningService: ScreeningService

    @GetMapping()
    fun findEntry(@RequestParam name: String): ResponseEntity<MatchResult> {
        logger.info("Received request to verify '$name'")
        val result = screeningService.checkName(name)
        return ResponseEntity(result, HttpStatus.OK)
    }


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addNewEntry(@RequestBody addRequest: AddRequest): ResponseEntity<APIResponse> {
        logger.info("Received request to verify '${addRequest.namem}'")

        return try {
            val success = screeningService.addSanctionedName(addRequest.namem)
            ResponseEntity(
                SuccessResponse(success = success),
                HttpStatus.CREATED
            )
        } catch (ex: Exception) {
            ResponseEntity(
                ErrorResponse(description = "An error occurred"),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }


    @PatchMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateEntry(@RequestBody updateRequest: UpdateRequest): ResponseEntity<APIResponse> {
        return try {
            val success = screeningService.updateSanctionedName(
                oldName = updateRequest.oldName, newName = updateRequest.newName
            )
            ResponseEntity(
                SuccessResponse(success = success),
                HttpStatus.OK
            )
        } catch (ex: Exception) {
            ResponseEntity(
                ErrorResponse(description = "An error occurred"),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }

    @DeleteMapping()
    fun deleteEntry(@RequestParam name: String): ResponseEntity<APIResponse> {

        return try {
            val success = screeningService.removeSanctionedName(name)
            ResponseEntity(
                SuccessResponse(success = success),
                HttpStatus.OK
            )
        } catch (ex: Exception) {
            ResponseEntity(
                ErrorResponse(description = "An error occurred"),
                HttpStatus.INTERNAL_SERVER_ERROR
            )
        }
    }


}