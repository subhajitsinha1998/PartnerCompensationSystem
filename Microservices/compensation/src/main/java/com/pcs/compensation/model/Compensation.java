package com.pcs.compensation.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "pcs_compensation")
public class Compensation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int compensationId;
    private String partnerName;
    private String planName;
    private String planType;
    private Float minPercent;
    private Float maxPercent;
    private Date startDate;
    private Date endDate;
}
