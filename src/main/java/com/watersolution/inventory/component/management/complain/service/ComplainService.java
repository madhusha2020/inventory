package com.watersolution.inventory.component.management.complain.service;

import com.watersolution.inventory.component.management.complain.model.api.ComplainList;
import com.watersolution.inventory.component.management.complain.model.db.Complain;

public interface ComplainService {

    ComplainList getAllComplains();

    Complain getComplainById(String complainId);

    Complain saveComplain(Complain complain);

    Complain updateComplain(Complain complain);
}
