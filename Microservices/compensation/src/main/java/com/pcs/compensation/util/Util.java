package com.pcs.compensation.util;

import org.springframework.stereotype.Component;

import com.pcs.compensation.exception.CustomException;
import com.pcs.compensation.model.Compensation;

@Component
public class Util {

    /**
     * Checks if a compensation object is having valid data or not
     * 
     * @param compensation
     * @throws CustomException throws custom exception if the object has inconsistent data
     */
    public void validCompensation(Compensation compensation){
        if (compensation.getEndDate().before(compensation.getStartDate()))
            throw new CustomException("End date should not be before start date");
        if (!(isValidPercentage(compensation.getMaxPercent()) && isValidPercentage(compensation.getMinPercent())))
            throw new CustomException("Percentage value should be an integer between 0 to 100");
        if (compensation.getMinPercent() >= compensation.getMaxPercent())
            throw new CustomException("Maximum percentage should be greater than minimum percentage");
    }

    /**
     * Checks if the number is between 0 and 100
     * 
     * @param percentage 
     * @return true if the number is between 0 and 100 else 100
     */
    private boolean isValidPercentage(Float percentage) {
        return (percentage >=0 && percentage <= 100);
    }
    
}
