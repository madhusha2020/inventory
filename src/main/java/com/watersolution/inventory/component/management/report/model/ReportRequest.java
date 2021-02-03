package com.watersolution.inventory.component.management.report.model;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.common.util.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ReportRequest extends ResponseDefault {

    private LocalDate fromDate;
    private LocalDate toDate;

    private long customerId;
    private long supplierId;
    private long employeeId;
    private long vehicleId;
    private long itemId;

    private List<Integer> statusList;

    public LocalDate getFromDate() {
        if(fromDate == null)
        {
            return LocalDate.now();
        }
        return fromDate;
    }

    public LocalDate getToDate() {
        if(toDate == null)
        {
            return LocalDate.now();
        }
        return toDate;
    }

    public List<Integer> getStatusList() {
        if(statusList == null || statusList.size() == 0)
        {
            return Status.getAllStatusAsList();
        }
        return statusList;
    }
}
