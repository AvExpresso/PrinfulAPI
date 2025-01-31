package com.avsemprize.printful.models.info;


import com.avsemprize.printful.models.SyncProduct;
import com.avsemprize.printful.models.SyncVariant;

/**
 * @author shuklaalok7
 * @since 1/01/2017
 */
public class SyncVariantInfo {
    /**
     * Information about the selected Sync Variant
     */
    private SyncVariant syncVariant;

    /**
     * Information about the Sync Product that the Sync Variant belongs to
     */
    private SyncProduct syncProduct;

    public SyncVariant getSyncVariant() {
        return syncVariant;
    }

    public void setSyncVariant(SyncVariant syncVariant) {
        this.syncVariant = syncVariant;
    }

    public SyncProduct getSyncProduct() {
        return syncProduct;
    }

    public void setSyncProduct(SyncProduct syncProduct) {
        this.syncProduct = syncProduct;
    }
}
