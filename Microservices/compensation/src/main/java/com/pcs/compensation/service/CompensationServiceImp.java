package com.pcs.compensation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.pcs.compensation.dao.CompensationRepository;
import com.pcs.compensation.model.Compensation;
import com.pcs.compensation.util.Util;

@Service
public class CompensationServiceImp implements CompensationService {
    
    @Autowired private CompensationRepository compensationRepository;

    @Autowired private Util util;

    @Override
    public Compensation createCompensation(Compensation compensation) {
        util.validCompensation(compensation);
        return compensationRepository.save(compensation);
    }

    @Override
    public Page<Compensation> getAllCompensation(Integer offset, Integer pageSize) {
        if (offset == null || pageSize == null)
            return compensationRepository.findAll(Pageable.unpaged());
        return compensationRepository.findAll(PageRequest.of(offset, pageSize));
    }

}
