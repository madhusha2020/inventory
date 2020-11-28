package com.watersolution.inventory.component.management.sales.util;

import com.watersolution.inventory.component.common.util.CustomerType;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class SaleServiceHelperImpl implements SaleServiceHelper {

    @Value("${customer.regular.discount:0.1}")
    private BigDecimal regularCusDiscount;
    @Value("${customer.external.discount:0.05}")
    private BigDecimal externalCusDiscount;

    @Override
    public Sale calculateSales(Sale sale, OrderItemsList orderItemsList) {
        orderItemsList.setTotal(BigDecimal.ZERO);
        CustomerType customerType = CustomerType.fromValue(orderItemsList.getOrder().getCustomer().getType());
        switch (customerType) {
            case REGULAR:
                orderItemsList.setDiscount(regularCusDiscount);
                break;
            case EXTERNAL:
                orderItemsList.setDiscount(externalCusDiscount);
                break;
        }
        orderItemsList.getOrderItems().stream().forEach(orderItem -> {
            orderItemsList.setTotal(orderItemsList.getTotal().add(new BigDecimal(orderItem.getItem().getLastprice() * orderItem.getQty())));
        });

        log.info("Total amount is :" + orderItemsList.getTotal());
        sale.setDiscount(orderItemsList.getTotal().multiply(orderItemsList.getDiscount()));
        log.info("Discount amount is :" + sale.getDiscount());
        sale.setTotal(orderItemsList.getTotal().subtract(orderItemsList.getTotal().multiply(orderItemsList.getDiscount())));
        log.info("Final amount is :" + sale.getTotal());
        return sale;
    }
}
