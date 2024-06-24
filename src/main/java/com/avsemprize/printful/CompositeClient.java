package com.avsemprize.printful;


import com.avsemprize.printful.clients.*;
import com.avsemprize.printful.enums.FileStatus;
import com.avsemprize.printful.enums.OrderStatus;
import com.avsemprize.printful.enums.SyncStatus;
import com.avsemprize.printful.models.*;
import com.avsemprize.printful.models.includable.Country;
import com.avsemprize.printful.models.info.*;

/**
 * @author shuklaalok7
 * @since 21/12/2016
 */
public class CompositeClient implements Client {

    private final CountryStateCodeApiClient countryStateCodeApiClient;
    private final EComPlatformSyncApiClient eComPlatformSyncApiClient;
    private final FileLibraryApiClient fileLibraryApiClient;
    private final OrdersApiClient ordersApiClient;
    private final ProductCatalogApiClient productCatalogApiClient;
    private final ShippingRateApiClient shippingRateApiClient;
    private final StoreInfoApiClient storeInfoApiClient;
    private final TaxRateApiClient taxRateApiClient;
    private final WebhookApiClient webhookApiClient;


    /**
     * @param apiKey
     */
    public CompositeClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey        YOUR_API_KEY
     * @param configuration configuration object
     */
    public CompositeClient(String apiKey, Configuration configuration) {
        this.countryStateCodeApiClient = new CountryStateCodeApiClient(apiKey, configuration);
        this.eComPlatformSyncApiClient = new EComPlatformSyncApiClient(apiKey, configuration);
        this.fileLibraryApiClient = new FileLibraryApiClient(apiKey, configuration);
        this.ordersApiClient = new OrdersApiClient(apiKey, configuration);
        this.productCatalogApiClient = new ProductCatalogApiClient(apiKey, configuration);
        this.shippingRateApiClient = new ShippingRateApiClient(apiKey, configuration);
        this.storeInfoApiClient = new StoreInfoApiClient(apiKey, configuration);
        this.taxRateApiClient = new TaxRateApiClient(apiKey, configuration);
        this.webhookApiClient = new WebhookApiClient(apiKey, configuration);
    }

    @Override
    public Response<Product> getAllProductList() {
        return productCatalogApiClient.getAllProductList();
    }

    @Override
    public Response<VariantInfo> getInfoAboutVariant(int variantId) {
        return productCatalogApiClient.getInfoAboutVariant(variantId);
    }

    @Override
    public Response<ProductInfo> getProductsVariantsList(int productId) {
        return productCatalogApiClient.getProductsVariantsList(productId);
    }

    @Override
    public Response<Order> getListOfOrders(OrderStatus status, int offset, int limit) {
        return ordersApiClient.getListOfOrders(status, offset, limit);
    }

    @Override
    public Response<Order> createANewOrder(Order order, boolean confirm, boolean updateExisting) {
        return ordersApiClient.createANewOrder(order, confirm, updateExisting);
    }

    @Override
    public Response<Order> getOrderData(int orderId) {
        return ordersApiClient.getOrderData(orderId);
    }

    @Override
    public Response<Order> getOrderData(String externalId) {
        return ordersApiClient.getOrderData(externalId);
    }

    @Override
    public Response<Order> cancelAnOrder(int orderId) {
        return ordersApiClient.cancelAnOrder(orderId);
    }

    @Override
    public Response<Order> cancelAnOrder(String externalId) {
        return ordersApiClient.cancelAnOrder(externalId);
    }

    @Override
    public Response<Order> updateOrderData(int orderId, Order order, boolean confirm) {
        return ordersApiClient.updateOrderData(orderId, order, confirm);
    }

    @Override
    public Response<Order> updateOrderData(String externalId, Order order, boolean confirm) {
        return ordersApiClient.updateOrderData(externalId, order, confirm);
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(int orderId) {
        return ordersApiClient.confirmDraftForFulfillment(orderId);
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(String externalId) {
        return ordersApiClient.confirmDraftForFulfillment(externalId);
    }

    @Override
    public Response<File> getListOfFiles(FileStatus status, int offset, int limit) {
        return fileLibraryApiClient.getListOfFiles(status, offset, limit);
    }

    @Override
    public Response<File> addANewFile(File file) {
        return fileLibraryApiClient.addANewFile(file);
    }

    @Override
    public Response<File> getFileInfo(int fileId) {
        return fileLibraryApiClient.getFileInfo(fileId);
    }

    @Override
    public Response<ShippingInfo> calculateShippingRates(ShippingRequest request) {
        return shippingRateApiClient.calculateShippingRates(request);
    }

    @Override
    public Response<Country> retrieveCountryList() {
        return countryStateCodeApiClient.retrieveCountryList();
    }

    @Override
    public Response<Country> retrieveStateListThatRequiresTaxCalculation() {
        return taxRateApiClient.retrieveStateListThatRequiresTaxCalculation();
    }

    @Override
    public Response<TaxInfo> calculateTaxRate(TaxAddressInfo taxAddressInfo) {
        return taxRateApiClient.calculateTaxRate(taxAddressInfo);
    }

    @Override
    public Response<WebhookInfo> getWebhookConfig() {
        return webhookApiClient.getWebhookConfig();
    }

    @Override
    public Response<WebhookInfo> setupWebhookConfig(WebhookInfo webhookInfo) {
        return webhookApiClient.setupWebhookConfig(webhookInfo);
    }

    @Override
    public Response<WebhookInfo> disableWebhookSupport() {
        return webhookApiClient.disableWebhookSupport();
    }

    @Override
    public Response<StoreData> getStoreInfo() {
        return storeInfoApiClient.getStoreInfo();
    }

    @Override
    public Response<PackingSlip> changeStorePackingSlip(PackingSlip packingSlip) {
        return storeInfoApiClient.changeStorePackingSlip(packingSlip);
    }

    @Override
    public Response<SyncProduct> getListOfSyncProducts(SyncStatus status, int offset, int limit) {
        return eComPlatformSyncApiClient.getListOfSyncProducts(status, offset, limit);
    }

    @Override
    public Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(long id) {
        return eComPlatformSyncApiClient.getInfoAboutSyncProductAndVariants(id);
    }

    @Override
    public Response<SyncProductInfo> getInfoAboutSyncProductAndVariants(String externalId) {
        return eComPlatformSyncApiClient.getInfoAboutSyncProductAndVariants(externalId);
    }

    @Override
    public Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(long id) {
        return eComPlatformSyncApiClient.unlinkAllSyncedVariantsOfProduct(id);
    }

    @Override
    public Response<SyncProductInfo> unlinkAllSyncedVariantsOfProduct(String externalId) {
        return eComPlatformSyncApiClient.unlinkAllSyncedVariantsOfProduct(externalId);
    }

    @Override
    public Response<SyncVariantInfo> getInfoAboutVariant(long id) {
        return eComPlatformSyncApiClient.getInfoAboutVariant(id);
    }

    @Override
    public Response<SyncVariantInfo> getInfoAboutVariant(String externalId) {
        return eComPlatformSyncApiClient.getInfoAboutVariant(externalId);
    }

    @Override
    public Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(long variantId, SyncVariant syncVariant) {
        return eComPlatformSyncApiClient.updateLinkedProductAndPrintFileInfo(variantId, syncVariant);
    }

    @Override
    public Response<SyncVariantInfo> updateLinkedProductAndPrintFileInfo(String externalVariantId, SyncVariant syncVariant) {
        return eComPlatformSyncApiClient.updateLinkedProductAndPrintFileInfo(externalVariantId, syncVariant);
    }

    @Override
    public Response<SyncVariantInfo> unlinkSyncedVariant(long variantId) {
        return eComPlatformSyncApiClient.unlinkSyncedVariant(variantId);
    }

    @Override
    public Response<SyncVariantInfo> unlinkSyncedVariant(String externalVariantId) {
        return eComPlatformSyncApiClient.unlinkSyncedVariant(externalVariantId);
    }

}
