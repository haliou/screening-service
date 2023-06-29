package md.lhv.verification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Verification service app
 *
 * @author Mamadou Diallo
 */
@SpringBootApplication
class VerificationService

fun main(args: Array<String>) {
    runApplication<VerificationService>(*args)
}
