package com.watersolution.inventory.component.management.report.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.management.delivery.model.db.Delivery;
import com.watersolution.inventory.component.management.delivery.repository.DeliveryRepository;
import com.watersolution.inventory.component.management.grn.model.db.Purchase;
import com.watersolution.inventory.component.management.grn.repository.PurchaseRepository;
import com.watersolution.inventory.component.management.order.model.db.Order;
import com.watersolution.inventory.component.management.order.repository.OrderRepository;
import com.watersolution.inventory.component.management.payment.customer.model.db.CustomerPayment;
import com.watersolution.inventory.component.management.payment.customer.repository.CustomerPaymentRepository;
import com.watersolution.inventory.component.management.payment.supplier.model.db.SupplierPayment;
import com.watersolution.inventory.component.management.payment.supplier.repository.SupplierPaymentRepository;
import com.watersolution.inventory.component.management.product.disposal.model.db.Disposal;
import com.watersolution.inventory.component.management.product.disposal.repository.DisposalRepository;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrder;
import com.watersolution.inventory.component.management.purchase.repository.PurchaseOrderRepository;
import com.watersolution.inventory.component.management.report.model.ReportRequest;
import com.watersolution.inventory.component.management.report.model.ReportResponse;
import com.watersolution.inventory.component.management.sales.model.db.Sale;
import com.watersolution.inventory.component.management.sales.repository.SaleRepository;
import com.watersolution.inventory.component.management.supplier.refund.model.db.SupplierRefund;
import com.watersolution.inventory.component.management.supplier.refund.repository.SupplierRefundRepository;
import com.watersolution.inventory.component.management.supplier.returns.model.db.SupplierReturn;
import com.watersolution.inventory.component.management.supplier.returns.repository.SupplierReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private CustomerPaymentRepository customerPaymentRepository;
    @Autowired
    private SupplierPaymentRepository supplierPaymentRepository;
    @Autowired
    private SupplierReturnRepository supplierReturnRepository;
    @Autowired
    private SupplierRefundRepository supplierRefundRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DisposalRepository disposalRepository;

    @Override
    public ReportResponse salesReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();
        List<Sale> saleList = new ArrayList<>();

        if (reportRequest.getCustomerId() != 0 && reportRequest.getItemId() != 0) {
            List<Sale> sales = saleRepository.findByCustomer_IdAndStatusInAndDateBetween(reportRequest.getCustomerId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapSaleDetails).collect(Collectors.toList());
            sales.forEach(sale -> {
                sale.getSaleInventoryList().forEach(saleInventory -> {
                    if (saleInventory.getInventory().getItem().getId() == reportRequest.getItemId()) {
                        saleList.add(sale);
                    }
                });
            });
            reportResponse.setSaleList(saleList.stream().map(this::mapSaleDetails).collect(Collectors.toList()));
        } else if (reportRequest.getItemId() != 0) {
            List<Sale> sales = saleRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapSaleDetails).collect(Collectors.toList());
            sales.forEach(sale -> {
                sale.getSaleInventoryList().forEach(saleInventory -> {
                    if (saleInventory.getInventory().getItem().getId() == reportRequest.getItemId()) {
                        saleList.add(sale);
                    }
                });
            });
            reportResponse.setSaleList(saleList.stream().map(this::mapSaleDetails).collect(Collectors.toList()));
        } else if (reportRequest.getCustomerId() != 0) {
            reportResponse.setSaleList(saleRepository.findByCustomer_IdAndStatusInAndDateBetween(reportRequest.getCustomerId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapSaleDetails).collect(Collectors.toList()));
        } else {
            reportResponse.setSaleList(saleRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapSaleDetails).collect(Collectors.toList()));
        }
        return reportResponse;
    }

    @Override
    public ReportResponse orderReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();

        if (reportRequest.getCustomerId() != 0) {
            reportResponse.setOrderList(orderRepository.findByCustomer_IdAndStatusInAndDoorderedBetween(reportRequest.getCustomerId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapOrderDetails).collect(Collectors.toList()));
        } else {
            reportResponse.setOrderList(orderRepository.findAllByStatusInAndDoorderedBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapOrderDetails).collect(Collectors.toList()));
        }
        return reportResponse;
    }

    @Override
    public ReportResponse purchaseOrderReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();
        if (reportRequest.getSupplierId() != 0) {
            reportResponse.setPurchaseOrderList(purchaseOrderRepository.findBySupplier_IdAndStatusInAndDoorderedBetween(reportRequest.getSupplierId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapPurchaseOrderDetails).collect(Collectors.toList()));
        } else {
            reportResponse.setPurchaseOrderList(purchaseOrderRepository.findAllByStatusInAndDoorderedBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapPurchaseOrderDetails).collect(Collectors.toList()));
        }
        return reportResponse;
    }

    @Override
    public ReportResponse purchaseReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();
        List<Purchase> purchaseList = new ArrayList<>();

        if (reportRequest.getSupplierId() != 0 && reportRequest.getItemId() != 0) {
            List<Purchase> purchases = purchaseRepository.findBySupplier_IdAndStatusInAndDateBetween(reportRequest.getSupplierId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapPurchaseDetails).collect(Collectors.toList());
            purchases.forEach(purchase -> {
                purchase.getPurchaseItems().forEach(purchaseItems -> {
                    if (purchaseItems.getItem().getId() == reportRequest.getItemId()) {
                        purchaseList.add(purchase);
                    }
                });
            });
            reportResponse.setPurchaseList(purchaseList.stream().map(this::mapPurchaseDetails).collect(Collectors.toList()));
        } else if (reportRequest.getItemId() != 0) {
            List<Purchase> purchases = purchaseRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapPurchaseDetails).collect(Collectors.toList());
            purchases.forEach(purchase -> {
                purchase.getPurchaseItems().forEach(purchaseItems -> {
                    if (purchaseItems.getItem().getId() == reportRequest.getItemId()) {
                        purchaseList.add(purchase);
                    }
                });
            });
            reportResponse.setPurchaseList(purchaseList.stream().map(this::mapPurchaseDetails).collect(Collectors.toList()));
        } else if (reportRequest.getSupplierId() != 0) {
            reportResponse.setPurchaseList(purchaseRepository.findBySupplier_IdAndStatusInAndDateBetween(reportRequest.getSupplierId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapPurchaseDetails).collect(Collectors.toList()));
        } else {
            reportResponse.setPurchaseList(purchaseRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapPurchaseDetails).collect(Collectors.toList()));
        }
        return reportResponse;
    }

    @Override
    public ReportResponse customerPaymentReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();

        if (reportRequest.getCustomerId() != 0) {
            reportResponse.setCustomerPaymentList(customerPaymentRepository.findBySale_Customer_IdAndStatusInAndDateBetween(reportRequest.getCustomerId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapCustomerPaymentDetails).collect(Collectors.toList()));
        } else {
            reportResponse.setCustomerPaymentList(customerPaymentRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapCustomerPaymentDetails).collect(Collectors.toList()));
        }
        return reportResponse;
    }

    @Override
    public ReportResponse supplierPaymentReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();

        if (reportRequest.getSupplierId() != 0) {
            reportResponse.setSupplierPaymentList(supplierPaymentRepository.findByPurchase_Supplier_IdAndStatusInAndDateBetween(reportRequest.getSupplierId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapSupplierPaymentDetails).collect(Collectors.toList()));
        } else {
            reportResponse.setSupplierPaymentList(supplierPaymentRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapSupplierPaymentDetails).collect(Collectors.toList()));
        }
        return reportResponse;
    }

    @Override
    public ReportResponse supplierReturnReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setSupplierReturnList(supplierReturnRepository.findAllByStatusInAndDorecivedBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapSupplierReturnDetails).collect(Collectors.toList()));
        return reportResponse;
    }

    @Override
    public ReportResponse supplierRefundReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();
        reportResponse.setSupplierRefundList(supplierRefundRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapSupplierRefundDetails).collect(Collectors.toList()));
        return reportResponse;
    }

    @Override
    public ReportResponse deliveryReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();
        List<Delivery> deliveryList = new ArrayList<>();

        if (reportRequest.getEmployeeId() != 0 && reportRequest.getVehicleId() != 0) {
            List<Delivery> deliveries = deliveryRepository.findByEmployee_IdAndStatusInAndDateBetween(reportRequest.getEmployeeId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapDeliveryDetails).collect(Collectors.toList());
            deliveries.forEach(delivery -> {
                if (delivery.getVehicle().getId() == reportRequest.getVehicleId()) {
                    deliveryList.add(delivery);
                }
            });
            reportResponse.setDeliveryList(deliveryList.stream().map(this::mapDeliveryDetails).collect(Collectors.toList()));
        } else if (reportRequest.getEmployeeId() != 0) {
            reportResponse.setDeliveryList(deliveryRepository.findByEmployee_IdAndStatusInAndDateBetween(reportRequest.getEmployeeId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapDeliveryDetails).collect(Collectors.toList()));
        } else if (reportRequest.getVehicleId() != 0) {
            reportResponse.setDeliveryList(deliveryRepository.findByVehicle_IdAndStatusInAndDateBetween(reportRequest.getVehicleId(), reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapDeliveryDetails).collect(Collectors.toList()));
        } else {
            reportResponse.setDeliveryList(deliveryRepository.findByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapDeliveryDetails).collect(Collectors.toList()));
        }
        return reportResponse;
    }

    @Override
    public ReportResponse disposalReport(ReportRequest reportRequest) {
        ReportResponse reportResponse = new ReportResponse();
        List<Disposal> disposalList = new ArrayList<>();

        if (reportRequest.getItemId() != 0) {
            List<Disposal> disposals = disposalRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapDisposalDetails).collect(Collectors.toList());
            disposals.forEach(disposal -> {
                disposal.getDisposalInventoryList().forEach(disposalInventory -> {
                    if (disposalInventory.getInventory().getItem().getId() == reportRequest.getItemId()) {
                        disposalList.add(disposal);
                    }
                });
            });
            reportResponse.setDisposalList(disposalList.stream().map(this::mapDisposalDetails).collect(Collectors.toList()));
        } else {
            reportResponse.setDisposalList(disposalRepository.findAllByStatusInAndDateBetween(reportRequest.getStatusList(), reportRequest.getFromDate(), reportRequest.getToDate()).stream().map(this::mapDisposalDetails).collect(Collectors.toList()));
        }
        return reportResponse;
    }

    private Sale mapSaleDetails(Sale sale) {
        sale.setName(sale.getCustomer().getName());
        sale.setEmail(sale.getCustomer().getEmail());
        sale.setContact1(sale.getCustomer().getContact1());
        return sale;
    }

    private Order mapOrderDetails(Order order) {
        order.setName(order.getCustomer().getName());
        order.setAddress(order.getCustomer().getAddress());
        order.setContact1(order.getCustomer().getContact1());
        order.setEmail(order.getCustomer().getEmail());
        order.setType(order.getCustomer().getType());
        return order;
    }

    private PurchaseOrder mapPurchaseOrderDetails(PurchaseOrder purchaseOrder) {
        purchaseOrder.setSupplierName(purchaseOrder.getSupplier().getName());
        purchaseOrder.setSupplierType(purchaseOrder.getSupplier().getType());
        return purchaseOrder;
    }

    private Purchase mapPurchaseDetails(Purchase purchase) {
        return purchase;
    }

    private CustomerPayment mapCustomerPaymentDetails(CustomerPayment customerPayment) {
        return customerPayment;
    }

    private SupplierPayment mapSupplierPaymentDetails(SupplierPayment supplierPayment) {
        return supplierPayment;
    }

    private SupplierReturn mapSupplierReturnDetails(SupplierReturn supplierReturn) {
        return supplierReturn;
    }

    private SupplierRefund mapSupplierRefundDetails(SupplierRefund supplierRefund) {
        return supplierRefund;
    }

    private Delivery mapDeliveryDetails(Delivery delivery) {
        if (delivery.getStatus().equals(Status.ACTIVE.getValue()) || delivery.getStatus().equals(Status.SUSPENDED.getValue())) {
            delivery.setEmployeeId(delivery.getEmployee().getId());
            delivery.setDeliveryempname(delivery.getEmployee().getName());
            delivery.setDeliveryempcontactno(delivery.getEmployee().getMobile());
            delivery.setVehicleId(delivery.getVehicle().getId());
            delivery.setDeliveryvehicletype(delivery.getVehicle().getVehicleType().getName());
            delivery.setDeliveryvehicleno(delivery.getVehicle().getNo());
        }
        return delivery;
    }

    private Disposal mapDisposalDetails(Disposal disposal) {
        return disposal;
    }
}
