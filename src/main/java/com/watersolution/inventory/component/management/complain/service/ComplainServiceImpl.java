package com.watersolution.inventory.component.management.complain.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.complain.model.api.ComplainList;
import com.watersolution.inventory.component.management.complain.model.db.Complain;
import com.watersolution.inventory.component.management.complain.repository.ComplainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ComplainServiceImpl implements ComplainService {

    @Autowired
    private ComplainRepository complainRepository;

    @Override
    public ComplainList getAllComplains() {
        return new ComplainList(complainRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapComplainDetails).collect(Collectors.toList()));
    }

    @Override
    public Complain getComplainById(String complainId) {
        return mapComplainDetails(complainRepository.findByIdAndStatusIn(Long.valueOf(complainId), Status.getAllStatusAsList()));
    }

    @Override
    public Complain saveComplain(Complain complain) {
        complain.setStatus(Status.ACTIVE.getValue());
        complain.fillCompulsory(complain.getUserId());
        return mapComplainDetails(complainRepository.save(complain));
    }

    @Override
    public Complain updateComplain(Complain complain) {
        complain.fillUpdateCompulsory(complain.getUserId());
        return mapComplainDetails(complainRepository.save(complain));
    }

    private Complain mapComplainDetails(Complain complain) {
        return complain;
    }
}
