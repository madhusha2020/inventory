package com.watersolution.inventory.component.management.test.service;

import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;

public interface ChemicalTestService {

    ChemicalTest saveChemicalTest(OrderItemsList orderItemsList);
}
