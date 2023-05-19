package com.pcs.compensation.service;

import org.springframework.data.domain.Page;

import com.pcs.compensation.model.Compensation;

public interface CompensationService {
    
    /**
     * Create a new compensation plan
     * 
     * @param compensation compensation to be created
     * @return newly created entity of {@code compensation}
     * @throws CustomException if the data is not valid
     */
    public Compensation createCompensation(Compensation compensation);

    /**
     * Fetch the list of all compensation plans as page object
     * 
     * @param offset the page number which starts from 0
     * @param pageSize number of record in each page, defaults is 5
     * @return pageble object with list of compensation in content
     */
    public Page<Compensation> getAllCompensation(Integer offset, Integer pageSize);
    
}
