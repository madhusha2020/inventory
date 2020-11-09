package com.watersolution.inventory.component.management.image.factory;

import com.watersolution.inventory.component.common.util.ErrorCodes;
import com.watersolution.inventory.component.entity.customer.model.db.Customer;
import com.watersolution.inventory.component.entity.employee.model.db.Employee;
import com.watersolution.inventory.component.entity.employee.service.EmployeeService;
import com.watersolution.inventory.component.exception.CustomException;
import com.watersolution.inventory.component.management.image.model.ImageModel;
import com.watersolution.inventory.component.management.image.util.ImageCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class ImageFactory {

    @Autowired
    private EmployeeService employeeService;

    public ImageModel uploadImage(ImageModel imageModel, String category, long id) {

        ImageCategory imageCategory = ImageCategory.fromValue(category);
        switch (imageCategory) {
            case EMPLOYEE:
                Employee employee = employeeService.getEmployee(id);
                employee.setPhoto(imageModel.getPhoto());
                employeeService.saveEmployee(employee);
                imageModel.setPhoto(employee.getPhoto());
                return imageModel;
            case SUPPLIER:
            case VEHICLE:
            case ITEM:
            default:
                throw new CustomException(ErrorCodes.BAD_REQUEST, "Invalid image category", Collections.singletonList("Invalid image category"));
        }
    }

    public ImageModel getImage(String category, long id) {

        ImageCategory imageCategory = ImageCategory.fromValue(category);
        switch (imageCategory) {
            case EMPLOYEE:
                Employee employee = employeeService.getEmployee(id);
                return new ImageModel(employee.getPhoto());
            case SUPPLIER:
            case VEHICLE:
            case ITEM:
            default:
                throw new CustomException(ErrorCodes.BAD_REQUEST, "Invalid image category", Collections.singletonList("Invalid image category"));
        }
    }
}
