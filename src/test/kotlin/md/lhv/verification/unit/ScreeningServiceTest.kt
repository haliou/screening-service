package md.lhv.verification.unit

import md.lhv.verification.core.ScreeningService
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ScreeningServiceTest {

    private val service: ScreeningService = TODO();

    @Test
    fun `test name check against sanctioned list`() {
        val result = service.checkName("Bin Laden")
        assertTrue(result.isMatch)

        val resultWithNoiseWords = service.checkName("Mr. Bin Laden")
        assertTrue(resultWithNoiseWords.isMatch)
    }

    @Test
    fun `test name check with spelling errors`() {
        val result = service.checkName("Bni Laden")
        assertTrue(result.isMatch)
    }

    @Test
    fun `test false positive name check`() {
        val result = service.checkName("Bin Laden")
        assertFalse(result.isMatch)
    }


}