package com.pcs.compensation.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pcs.compensation.dto.CompensationDto;
import com.pcs.compensation.model.Compensation;
import com.pcs.compensation.service.CompensationService;

@RestController
public class CompensationControllerImpl implements CompensationController {

    @Autowired
    CompensationService compensationService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public ResponseEntity<CompensationDto> createCompensation(@RequestBody CompensationDto compensationDto) {
        Compensation compensation = modelMapper.map(compensationDto, Compensation.class);
        Compensation createdCompensation = compensationService.createCompensation(compensation);
        CompensationDto newCompensation = modelMapper.map(createdCompensation, CompensationDto.class);
        return new ResponseEntity<>(newCompensation, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<CompensationDto>> getAllCompensation(@RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer pageSize) {
        Page<CompensationDto> compensations = compensationService.getAllCompensation(offset, pageSize)
                .map(compensation -> modelMapper.map(compensation, CompensationDto.class));
        return new ResponseEntity<>(compensations, HttpStatus.OK);
    }

}
