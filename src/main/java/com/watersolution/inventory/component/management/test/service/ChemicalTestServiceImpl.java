package com.watersolution.inventory.component.management.test.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;
import com.watersolution.inventory.component.management.test.repository.ChemicalTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class ChemicalTestServiceImpl implements ChemicalTestService {

    @Autowired
    private ChemicalTestRepository chemicalTestRepository;

    @Transactional
    @Override
    public ChemicalTest saveChemicalTest(OrderItemsList orderItemsList) {

        ChemicalTest chemicalTest = new ChemicalTest();
        chemicalTest.setCode("");
        chemicalTest.setDescription("");
        chemicalTest.setPrice(orderItemsList.getTotal());
        chemicalTest.setDorequested(LocalDate.now());
        chemicalTest.setAddress(orderItemsList.getOrder().getCustomer().getAddress());
        chemicalTest.setStatus(Status.PENDING.getValue());
        chemicalTest.fillCompulsory(orderItemsList.getUserId());

        return chemicalTestRepository.save(chemicalTest);
    }
}
