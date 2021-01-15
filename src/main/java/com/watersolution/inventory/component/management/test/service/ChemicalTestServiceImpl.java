package com.watersolution.inventory.component.management.test.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.common.validator.CustomValidator;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.test.model.api.ChemicalTestList;
import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;
import com.watersolution.inventory.component.management.test.repository.ChemicalTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class ChemicalTestServiceImpl implements ChemicalTestService {

    @Autowired
    private CustomValidator customValidator;
    @Autowired
    private ChemicalTestRepository chemicalTestRepository;

    @Override
    public ChemicalTestList getAllChemicalTests() {
        return new ChemicalTestList(chemicalTestRepository.findAllByStatusIn(Status.getAllStatusAsList()).stream().map(this::mapChemicalTestDetails).collect(Collectors.toList()));
    }

    @Override
    public ChemicalTest getChemicalTestById(String testId) {
        customValidator.validateNullOrEmpty(testId, "test Id");
        ChemicalTest chemicalTest = chemicalTestRepository.findByIdAndStatusIn(Long.valueOf(testId), Status.getAllStatusAsList());
        customValidator.validateFoundNull(chemicalTest, "chemical Test");
        return mapChemicalTestDetails(chemicalTest);
    }

    @Override
    public ChemicalTest updateChemicalTest(ChemicalTest chemicalTest) {
        customValidator.validateFoundNull(chemicalTest.getId(), "test Id");
        ChemicalTest savedChemicalTest = chemicalTestRepository.findByIdAndStatusIn(Long.valueOf(chemicalTest.getId()), Status.getAllStatusAsList());
        customValidator.validateFoundNull(savedChemicalTest, "chemical Test");
        savedChemicalTest.setResult(chemicalTest.getResult());
        savedChemicalTest.setDotested(chemicalTest.getDotested());
        savedChemicalTest.setDodone(chemicalTest.getDodone());
        savedChemicalTest.fillUpdateCompulsory(chemicalTest.getUserId());
        return mapChemicalTestDetails(chemicalTestRepository.save(savedChemicalTest));
    }

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

    private ChemicalTest mapChemicalTestDetails(ChemicalTest chemicalTest) {
        return chemicalTest;
    }
}
