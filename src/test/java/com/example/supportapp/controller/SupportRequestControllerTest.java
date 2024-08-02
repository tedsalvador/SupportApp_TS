package com.example.supportapp.controller;

import com.example.supportapp.model.SupportRequest;
import com.example.supportapp.service.SupportRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
//import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class SupportRequestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SupportRequestService supportRequestService;

    @InjectMocks
    private SupportRequestController supportRequestController;

    private SupportRequest supportRequest;

    @BeforeEach
    public void setUp() {
        supportRequest = new SupportRequest();
        supportRequest.setIdSupport(1L);
        supportRequest.setDateRequest(LocalDateTime.of(2024, 7, 25, 10, 0));
        supportRequest.setIdUser(1L);
        supportRequest.setDescription("Support needed for issue #1234");
        supportRequest.setSupportType("Technical");
        supportRequest.setStatus("Open");
        supportRequest.setDateAttention(LocalDateTime.of(2024, 7, 25, 10, 0));

        mockMvc = MockMvcBuilders.standaloneSetup(supportRequestController).build();
    }

    @Test
    public void testCreateSupportRequest() throws Exception {
        when(supportRequestService.createSupportRequest(any(SupportRequest.class))).thenReturn(supportRequest);

        mockMvc.perform(post("/api/support_requests")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"dateRequest\":\"2024-07-25T10:00:00\",\"idUser\":1,\"description\":\"Support needed for issue #1234\",\"supportType\":\"Technical\",\"status\":\"Open\",\"date\":\"2024-07-25\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Support needed for issue #1234"));
    }

    @Test
    public void testGetSupportRequestById() throws Exception {
        when(supportRequestService.getSupportRequestById(1L)).thenReturn(Optional.of(supportRequest));

        mockMvc.perform(get("/api/support_requests/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Support needed for issue #1234"));
    }
}
