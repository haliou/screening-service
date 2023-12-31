package md.lhv.screening.integration

import com.fasterxml.jackson.databind.ObjectMapper
import md.lhv.screening.core.ScreeningService
import md.lhv.screening.rest.http.SuccessResponse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import md.lhv.screening.core.model.MatchResult
import md.lhv.screening.rest.http.AddRequest
import md.lhv.screening.rest.http.UpdateRequest

@SpringBootTest
@AutoConfigureMockMvc
class ScreeningControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var screeningService: ScreeningService


    @BeforeEach
    fun setup() {
        reset(screeningService)
    }

    @Test
    fun `findEntry returns a success match result`() {
        val name = "Bin Laden"
        val matchResult = MatchResult(isMatch = true)
        `when`(screeningService.checkName(name)).thenReturn(matchResult)

        mockMvc.perform(
            get("/api/v1/screening")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(matchResult)))

        verify(screeningService, times(1)).checkName(name)
    }

    @Test
    fun `addNewEntry adds a new entry`() {
        val addRequest = AddRequest("Bin Laden")
        `when`(screeningService.addSanctionedName(addRequest.name)).thenReturn(true)

        mockMvc.perform(
            post("/api/v1/screening")
                .content(objectMapper.writeValueAsString(addRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isCreated)
            .andExpect(content().json(objectMapper.writeValueAsString(SuccessResponse(true))))

        verify(screeningService, times(1)).addSanctionedName(addRequest.name)
    }

    @Test
    fun `updateEntry updates an entry`() {
        val updateRequest = UpdateRequest("Bin Laden", "Saddam Hussein")
        `when`(screeningService.updateSanctionedName(updateRequest.oldName, updateRequest.newName)).thenReturn(true)

        mockMvc.perform(
            patch("/api/v1/screening")
                .content(objectMapper.writeValueAsString(updateRequest))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(SuccessResponse(true))))

        verify(screeningService, times(1)).updateSanctionedName(updateRequest.oldName, updateRequest.newName)
    }

    @Test
    fun `deleteEntry deletes an entry`() {
        val name = "Bin Laden"
        `when`(screeningService.removeSanctionedName(name)).thenReturn(true)

        mockMvc.perform(
            delete("/api/v1/screening")
                .param("name", name)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().json(objectMapper.writeValueAsString(SuccessResponse(true))))

        verify(screeningService, times(1)).removeSanctionedName(name)
    }
}
