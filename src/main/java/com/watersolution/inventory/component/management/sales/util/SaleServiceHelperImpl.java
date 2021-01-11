package com.watersolution.inventory.component.management.sales.util;

import com.watersolution.inventory.component.entity.discount.service.DiscountService;
import com.watersolution.inventory.component.management.order.model.api.OrderItemsList;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class SaleServiceHelperImpl implements SaleServiceHelper {

    @Autowired
    private DiscountService discountService;

    @Override
    public Sale calculateSales(Sale sale, OrderItemsList orderItemsList) {
        orderItemsList.setTotal(BigDecimal.ZERO);
        orderItemsList.setDiscount(discountService.getDiscount("CUSTOMER", orderItemsList.getOrder().getCustomer().getType().toUpperCase()).getDiscount());
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
