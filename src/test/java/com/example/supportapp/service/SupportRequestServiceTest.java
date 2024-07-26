package com.example.supportapp.service;

import com.example.supportapp.model.SupportRequest;
import com.example.supportapp.repository.SupportRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SupportRequestServiceTest {

    @Mock
    private SupportRequestRepository supportRequestRepository;

    @InjectMocks
    private SupportRequestService supportRequestService;

    private SupportRequest supportRequest;

    @BeforeEach
    public void setUp() {
        supportRequest = new SupportRequest();
        supportRequest.setIdSupport(1L);
        supportRequest.setDateRequest(LocalDateTime.now());
        supportRequest.setIdUser(1L);
        supportRequest.setDescription("Sample support request");
        supportRequest.setType("Technical");
        supportRequest.setStatus("Open");
        supportRequest.setDateAttention(null);
    }

    @Test
    public void testCreateSupportRequest() {
        when(supportRequestRepository.save(any(SupportRequest.class))).thenReturn(supportRequest);
        SupportRequest createdRequest = supportRequestService.createSupportRequest(supportRequest);
        assertThat(createdRequest.getDescription(), is(equalTo(supportRequest.getDescription())));
    }

    @Test
    public void testGetSupportRequestById() {
        when(supportRequestRepository.findById(anyLong())).thenReturn(Optional.of(supportRequest));
        Optional<SupportRequest> foundRequest = supportRequestService.getSupportRequestById(1L);
        assertThat(foundRequest.isPresent(), is(true));
        assertThat(foundRequest.get().getDescription(), is(equalTo(supportRequest.getDescription())));
    }

    @Test
    public void testGetAllSupportRequests() {
        when(supportRequestRepository.findAll()).thenReturn(List.of(supportRequest));
        List<SupportRequest> requests = supportRequestService.getAllSupportRequests();
        assertThat(requests.size(), is(equalTo(1)));
        assertThat(requests.get(0).getDescription(), is(equalTo(supportRequest.getDescription())));
    }

    @Test
    public void testUpdateSupportRequest() {
        SupportRequest updatedRequest = new SupportRequest();
        updatedRequest.setIdSupport(1L);
        updatedRequest.setDateRequest(LocalDateTime.now());
        updatedRequest.setIdUser(1L);
        updatedRequest.setDescription("Updated description");
        updatedRequest.setType("Technical");
        updatedRequest.setStatus("Closed");
        updatedRequest.setDateAttention(LocalDateTime.now());

        when(supportRequestRepository.existsById(1L)).thenReturn(true);
        when(supportRequestRepository.save(any(SupportRequest.class))).thenReturn(updatedRequest);

        SupportRequest result = supportRequestService.updateSupportRequest(1L, updatedRequest);
        assertThat(result.getDescription(), is(equalTo(updatedRequest.getDescription())));
    }

    @Test
    public void testDeleteSupportRequest() {
        supportRequestService.deleteSupportRequest(1L);
        verify(supportRequestRepository).deleteById(1L);
    }
}
