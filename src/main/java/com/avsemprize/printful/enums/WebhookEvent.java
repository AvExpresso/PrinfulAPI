package com.avsemprize.printful.enums;

/**
 * See https://www.theprintful.com/docs/webhooks
 *
 * @author shuklaalok7
 * @since 1/01/2017
 */
public enum WebhookEvent {

    /**
     * Calls POST http://example.com/your-webhook-url
     * <p>
     * Is called when a shipment with all or part of the ordered items is shipped.
     * </p>
     * <p>
     * If the order is shipped in multiple packages, this event will be called for every package sent.
     * </p>
     * <p>
     * If some items are reshipped, a shipping notification will be sent again for the same items.
     * </p>
     */
    PACKAGE_SHIPPED,
    /**
     * Is called when a shipment is processed as returned to the fulfillment facility. To learn more about the reasons why a shipment might be returned, take a look at Printful's Return Policy
     */
    PACKAGE_RETURNED,


    ORDER_PUT_HOLD,
    ORDER_PUT_HOLD_APPROVAL,
    /**
     * Is called when the order is first created.
     */
    ORDER_CREATED,
    /**
     * Is called when an existing order gets updated for any reason - including things that are covered with other webhooks like order_canceled.
     */
    ORDER_UPDATED,
    /**
     * Calls POST http://example.com/your-webhook-url
     * <p>
     * Is called when a confirmed order changes its status to {@link OrderStatus#FAILED}.<p>
     * It can happen if printfiles can not be downloaded, are not valid image files or when charging
     * of the payment card fails.
     */
    ORDER_FAILED,

    /**
     * Calls POST http://example.com/your-webhook-url<p>
     * Is called when a confirmed order changes its status to {@link OrderStatus#CANCELED}.<p>
     * It can happen when a submitted order is canceled from the dashboard or through the API or
     * when the order is cancelled by the Printful staff.
     */
    ORDER_CANCELED,

    /**
     * Is called when a new product or variant is imported from store's e-commerce integration.
     *
     * @see in.clayfish.printful.clients.EComPlatformSyncApiClient
     */
    PRODUCT_SYNCED,
    /**
     * Is called when a new product or variant is created or updated in any way.
     *
     * See Ecommerce Platform Sync API
     */
    PRODUCT_UPDATED,
    /**
     * Is called when a new product or variant is deleted.
     *
     * See Ecommerce Platform Sync API
     */
    PRODUCT_DELETED,

    /**
     * Is called when stock is updated for some of product's variants.
     * <p>
     * Contains product id and ids of it's discontinued variants and variants that are out of stock.
     * Variant ids that are not present should be considered as active and in stock.
     */
    STOCK_UPDATED;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
