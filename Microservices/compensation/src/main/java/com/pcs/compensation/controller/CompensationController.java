package com.pcs.compensation.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pcs.compensation.dto.CompensationDto;

@CrossOrigin
@RequestMapping(path = "/api/v1/pcs")
public interface CompensationController {

    /**
     * Post request which for creating a new compensation
     * 
     * @param compensationDto compensation details received in the request body
     * @return a response entity with status code 200 and compensation data in body
     *         on success
     */
    @PostMapping(path = "/compensation")
    public ResponseEntity<CompensationDto> createCompensation(@RequestBody CompensationDto compensationDto);

    /**
     * Get request which fetches the record of compensation. Can be pagenated or all
     * recordes can be fetched.
     * 
     * @param offset   the page number whose record to retrieve
     * @param pageSize the number of records in each page
     * @return page object with content as the list of compensation record
     */
    @GetMapping(path = "/compensation")
    public ResponseEntity<Page<CompensationDto>> getAllCompensation(@RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer pageSize);

}
