package com.avsemprize.printful.models;

import com.avsemprize.printful.models.includable.ItemOption;
import com.avsemprize.printful.models.includable.ProductVariant;

import java.util.List;

/**
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class SyncVariant extends Entity {

    /**
     * Variant ID from the E-commerce platform
     */
    private String externalId;

    /**
     * Sync Product ID that this variant belongs to
     */
    private int syncProductId;

    /**
     * Sync Variant name
     */
    private String name;

    /**
     * Indicates if this Sync Variant is properly linked with Printful product
     */
    private boolean synced;

    /**
     * Printful Variant ID that this Sync Variant is synced to
     */
    private int variantId;

    /**
     * Short information about the Printful Product and Variant that this Sync Variant is synced to
     */
    private ProductVariant product;

    /**
     * Attached printfiles / preview images
     */
    private List<File> files;
    /**
     * Additional options for the configured product/variant
     */
    private List<ItemOption> options;

    private String color;

    private String size;

    private Double retailPrice;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public int getSyncProductId() {
        return syncProductId;
    }

    public void setSyncProductId(int syncProductId) {
        this.syncProductId = syncProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

    public int getVariantId() {
        return variantId;
    }

    public void setVariantId(int variantId) {
        this.variantId = variantId;
    }

    public ProductVariant getProduct() {
        return product;
    }

    public void setProduct(ProductVariant product) {
        this.product = product;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<ItemOption> getOptions() {
        return options;
    }

    public void setOptions(List<ItemOption> options) {
        this.options = options;
    }
}
