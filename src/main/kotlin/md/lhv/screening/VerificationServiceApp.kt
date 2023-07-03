package md.lhv.screening

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate

/**
 * Verification service app
 *
 * @author Mamadou Diallo
 */
@SpringBootApplication
@EnableScheduling
class VerificationService{

    @Bean
    fun restTemplate() = RestTemplate()
}

fun main(args: Array<String>) {
    runApplication<VerificationService>(*args)
}


