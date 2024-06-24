package com.avsemprize.printful.models.info;

import com.avsemprize.printful.models.Product;
import com.avsemprize.printful.models.Variant;

import java.util.List;



/**
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ProductInfo {

    /**
     * Information about the selected product
     */
    private Product product;

    /**
     * Variants available for the selected product
     */
    private List<Variant> variants;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }


}
