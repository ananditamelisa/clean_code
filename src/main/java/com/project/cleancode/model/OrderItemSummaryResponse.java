package com.project.cleancode.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemSummaryResponse {

  private String orderItemId;
  private String orderId;
  private Date createdDate;
  private String customerFullName;
  private String merchantSku;
  private String itemName;
  private Integer quantity;
  private Double price;
  private Double merchantAdjustment;
  private String logisticsProductCode;
  private String logisticsProductName;
  private String orderItemStatus;
  private String orderType;
  private String pickupPointCode;
  private String pickupPointName;
  private String productTypeCode;
  private String productTypeName;
  private String skuCode;
  private String itemSku;
  private String merchantCode;
  private String storeName;
  private Date endStoreClosingDate;
  private Date startStoreClosingDate;
  private Date autoCancelTimestamp;
  private Boolean lateFulfillment;
  private Boolean installationRequired;
  private Boolean isInternationalSeller;
  private String awbNumber;
  private String shippingInstruction;
  private String merchantDeliveryType;
  private String awbValidityStatus;
  private String merchantCommissionType;
  private String salesPersonName;
  private Date orderDate;
  private Date statusFPUpdatedTimestamp;
  private String logisticsOptionName;
  private String logisticsOptionCode;
  private Integer bookingHourStartInSecond;
  private Integer bookingHourEndInSecond;
  private Boolean isMerchantPaid;
  private String promoCombinationId;
  private String promoBundlingId;
  private String promoBundlingType;
  private String settlementCode;
  private boolean settlementCodeExpired;
  private boolean instantPickup;
  private String packageId;
  private Boolean packageCreated;
  private boolean isCashlessHandover;
  private Integer cashlessStatusUpdateSla;
}
