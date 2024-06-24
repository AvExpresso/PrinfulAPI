package com.avsemprize.printful.models.info;

import com.avsemprize.printful.models.SyncProduct;
import com.avsemprize.printful.models.SyncVariant;

import java.util.List;



/**
 * Sync Product data and array of Sync Variants
 *
 * @author shuklaalok7
 * @since 29/12/2016
 */
public class SyncProductInfo {

    /**
     * Information about the selected product
     */
    private SyncProduct syncProduct;
    private List<SyncVariant> syncVariants;

    public SyncProduct getSyncProduct() {
        return syncProduct;
    }

    public void setSyncProduct(SyncProduct syncProduct) {
        this.syncProduct = syncProduct;
    }

    public List<SyncVariant> getSyncVariants() {
        return syncVariants;
    }

    public void setSyncVariants(List<SyncVariant> syncVariants) {
        this.syncVariants = syncVariants;
    }
}
