package md.lhv.screening.rest

import md.lhv.screening.core.ScreeningService
import md.lhv.screening.core.model.MatchResult
import md.lhv.screening.rest.http.APIResponse
import md.lhv.screening.rest.http.SuccessResponse
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


    @PostMapping()
    fun addNewEntry(@RequestBody name: String): ResponseEntity<APIResponse> {
        val success = screeningService.addSanctionedName(name)
        return ResponseEntity(
            SuccessResponse(success = success),
            HttpStatus.CREATED
        )
    }


    @PatchMapping()
    fun updateEntry(@RequestParam oldName: String, @RequestParam newName: String): ResponseEntity<APIResponse> {
        val success = screeningService.updateSanctionedName(oldName = oldName, newName = newName)
        return ResponseEntity(
            SuccessResponse(success = success),
            HttpStatus.OK
        )
    }

    @DeleteMapping()
    fun deleteEntry(@RequestParam name: String): ResponseEntity<APIResponse> {
        val success = screeningService.removeSanctionedName(name)
        return ResponseEntity(
            SuccessResponse(success = success),
            HttpStatus.OK
        )
    }


}