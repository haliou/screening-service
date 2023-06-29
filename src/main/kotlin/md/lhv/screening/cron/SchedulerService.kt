package md.lhv.screening.cron

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


/**
 *
 */
@Service
class SchedulerService(private val restTemplate: RestTemplate) {

    private var logger: Logger = LoggerFactory.getLogger(SchedulerService::class.java)

    @Value("\${sanctioned.list.url}")
    private lateinit var sanctionedListUrl: String


    // Run every day at 12 PM
    @Scheduled(cron =  "0 0 12 * * ?", zone = "Europe/London")
    fun fetchData() {
        val url = sanctionedListUrl

        // Using RestTemplate to make a GET call
        val response: ResponseEntity<String> = restTemplate.exchange(url, HttpMethod.GET, null, String::class.java)

        if (response.statusCode.is2xxSuccessful) {
            val body = response.body
            //TODO(store the data into the database)
        } else {
            logger.error("Failed to fetch data from $url")
        }
    }
}