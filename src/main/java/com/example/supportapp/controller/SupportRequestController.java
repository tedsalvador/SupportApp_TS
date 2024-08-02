package com.example.supportapp.controller;

import com.example.supportapp.model.SupportRequest;
import com.example.supportapp.repository.SupportRequestRepository;

import com.example.supportapp.service.SupportRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RequestMapping("/api/support_requests")
@RestController
@RequestMapping("/api/support_requests")
public class SupportRequestController {

    private SupportRequestService supportRequestService;
    private SupportRequestRepository supportRequestRepository;

    @Autowired
    public SupportRequestController(SupportRequestRepository supportRequestRepository) {
        this.supportRequestRepository = supportRequestRepository;
    }

    //@GetMapping("/support_requests")
    @GetMapping
    public List<SupportRequest> getAllSupportRequests() {
        return supportRequestRepository.findAll();
    }

/*     @GetMapping("/{id}")
    public ResponseEntity<SupportRequest> getSupportRequestById(@PathVariable Long id) {
         return supportRequestService.getSupportRequestById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    } */

    @GetMapping("/{id}")
    public ResponseEntity<SupportRequest> getSupportRequestById(@PathVariable Long id) {
        Optional<SupportRequest> supportRequest = supportRequestService.findById(id);
        return supportRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public SupportRequest createSupportRequest(@RequestBody SupportRequest supportRequest) {
        return supportRequestService.createSupportRequest(supportRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportRequest> updateSupportRequest(@PathVariable Long id, @RequestBody SupportRequest supportRequestDetails) {
        return ResponseEntity.ok(supportRequestService.updateSupportRequest(id, supportRequestDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupportRequest(@PathVariable Long id) {
        supportRequestService.deleteSupportRequest(id);
        return ResponseEntity.noContent().build();
    }
}
