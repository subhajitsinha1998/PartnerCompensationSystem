package com.pcs.compensation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CompensationDto {
    private int compensationId;
    private String partnerName;
    private String planName;
    private String planType;
    private Float minPercent;
    private Float maxPercent;
    private Date startDate;
    private Date endDate;
}
