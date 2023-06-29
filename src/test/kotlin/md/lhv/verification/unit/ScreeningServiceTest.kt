package md.lhv.verification.unit

import io.mockk.coEvery
import io.mockk.mockk
import md.lhv.verification.core.ScreeningRepository
import md.lhv.verification.core.ScreeningService
import md.lhv.verification.service.ScreeningServiceImpl
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
        coEvery { screeningRepository.findAll() } returns listOf("Bin Laden", "Pablo Escobar")

        val result = screeningService.checkName("Bin Laden")
        assertTrue(result.isMatch)

        val resultWithNoiseWords = screeningService.checkName("Mr. Bin Laden")
        assertTrue(resultWithNoiseWords.isMatch)
    }

    @Test
    fun `test name check with spelling errors`() {
        coEvery { screeningRepository.findAll() } returns listOf("Bin Laden", "Pablo Escobar")

        val result = screeningService.checkName("Bni Laden")
        assertTrue(result.isMatch)
    }

    @Test
    fun `test false positive name check`() {
        coEvery { screeningRepository.findAll() } returns listOf("Pablo Escobar")

        val result = screeningService.checkName("Bin Laden")
        assertFalse(result.isMatch)
    }

    @Test
    fun `test add sanctioned name`() {
        val success = screeningService.addSanctionedName("Saddam Hussein")
        assertTrue(success)
    }

    @Test
    fun `test update sanctioned name`() {
        screeningService.addSanctionedName("Saddam Hussein")
        val success = screeningService.updateSanctionedName("Saddam Hussein", "Hussein Saddam")
        assertTrue(success)
        val result = screeningService.checkName("Bin Laden")
        assertTrue(result.isMatch)
    }

    @Test
    fun `test remove sanctioned name`() {
        screeningService.addSanctionedName("Bin Laden")
        val success = screeningService.removeSanctionedName("Bin Laden")
        assertTrue(success)
        val result = screeningService.checkName("Bin Laden")
        assertFalse(result.isMatch)
    }


}