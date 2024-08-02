package com.example.supportapp.service;

import com.example.supportapp.model.SupportRequest;
import com.example.supportapp.repository.SupportRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportRequestService {

    @Autowired
    private SupportRequestRepository supportRequestRepository;

    public SupportRequest createSupportRequest(SupportRequest supportRequest) {
        return supportRequestRepository.save(supportRequest);
    }

    public Optional<SupportRequest> getSupportRequestById(Long id) {
        return supportRequestRepository.findById(id);
    }

    public List<SupportRequest> getAllSupportRequests() {
        return supportRequestRepository.findAll();
    }

    public SupportRequest updateSupportRequest(Long id, SupportRequest supportRequest) {
        if (supportRequestRepository.existsById(id)) {
            supportRequest.setIdSupport(id);
            return supportRequestRepository.save(supportRequest);
        }
        return null;
    }

    public void deleteSupportRequest(Long id) {
        supportRequestRepository.deleteById(id);
    }

 

    

    public Optional<SupportRequest> findById(Long idSupport) {
        return supportRequestRepository.findById(idSupport);
    }

    public SupportRequest save(SupportRequest supportRequest) {
        return supportRequestRepository.save(supportRequest);
    }

    public void deleteById(Long idSupport) {
        supportRequestRepository.deleteById(idSupport);
    }
}
