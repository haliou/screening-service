package md.lhv.screening.unit

import io.mockk.coEvery
import io.mockk.mockk
import md.lhv.screening.core.ScreeningService
import md.lhv.screening.repository.ScreeningRepository
import md.lhv.screening.repository.db.SanctionedPerson
import md.lhv.screening.service.ScreeningServiceImpl
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScreeningServiceTest {

    private lateinit var screeningRepository: ScreeningRepository

    private lateinit var screeningService: ScreeningService

    @BeforeEach
    fun setup() {
        screeningRepository = mockk<ScreeningRepository>()
        screeningService = ScreeningServiceImpl(screeningRepository)
    }

    @Test
    fun `test name check against sanctioned list`() {
        coEvery { screeningRepository.findAll() } returns listOf(
            SanctionedPerson(name = "Bin Laden"),
            SanctionedPerson(name = "Pablo Escobar")
        )

        val result = screeningService.checkName("Bin Laden")
        assertTrue(result.isMatch)

        val resultWithNoiseWords = screeningService.checkName("Mr. Bin Laden")
        assertTrue(resultWithNoiseWords.isMatch)
    }

    @Test
    fun `test name check with spelling errors`() {
        coEvery { screeningRepository.findAll() } returns listOf(
            SanctionedPerson(name = "Bin Laden"),
            SanctionedPerson(name = "Pablo Escobar")
        )

        val result = screeningService.checkName("Bni Laden")
        assertTrue(result.isMatch)
    }

    @Test
    fun `test name check with cases`() {
        coEvery { screeningRepository.findAll() } returns listOf(
            SanctionedPerson(name = "Bin Laden"),
        )

        val result = screeningService.checkName("bin laden")
        assertTrue(result.isMatch)
    }

    @Test
    fun `test name check with noise words`() {
        coEvery { screeningRepository.findAll() } returns listOf(
            SanctionedPerson(name = "Bin Laden"),
        )

        val result = screeningService.checkName("bin the an laden")
        assertTrue(result.isMatch)
    }

    @Test
    fun `test false positive name check`() {
        coEvery { screeningRepository.findAll() } returns listOf(
            SanctionedPerson(name = "Pablo Escobar")
        )

        val result = screeningService.checkName("Bin Laden")
        assertFalse(result.isMatch)
    }


}