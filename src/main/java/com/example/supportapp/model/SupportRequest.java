package com.example.supportapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.time.LocalDateTime;


@Entity
//@Table (name = "support_request" )
public class SupportRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_support")
    private Long idsupport;

    @Column(name = "date_Request")
    private LocalDateTime dateRequest;

    @Column(name = "id_User")
    private Long idUser;

    private String description;

    @Column(name = "support_Type")
    private String supportType;

    private String status;

    @Column(name = "date_Attention")
    private LocalDateTime dateAttention;

    // Getters y setters

    public Long getIdSupport() {
        return idsupport;
    }

    public void setIdSupport(Long idsupport) {
        this.idsupport = idsupport;
    }

    public LocalDateTime getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDateTime dateRequest) {
        this.dateRequest = dateRequest;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupportType() {
        return supportType;
    }

    public void setSupportType(String type) {
        this.supportType = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDateAttention() {
        return dateAttention;
    }

    public void setDateAttention(LocalDateTime dateAttention) {
        this.dateAttention = dateAttention;
    }
}
