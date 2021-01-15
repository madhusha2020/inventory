package com.watersolution.inventory.component.management.test.service;

import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.test.model.api.ChemicalTestList;
import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;

public interface ChemicalTestService {

    ChemicalTestList getAllChemicalTests();

    ChemicalTest getChemicalTestById(String testId);

    ChemicalTest updateChemicalTest(ChemicalTest chemicalTest);

    ChemicalTest saveChemicalTest(OrderItemsList orderItemsList);
}
