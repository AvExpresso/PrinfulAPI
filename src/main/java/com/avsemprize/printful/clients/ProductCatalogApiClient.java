package com.avsemprize.printful.clients;


import com.avsemprize.printful.SimpleClient;
import com.avsemprize.printful.models.Configuration;
import com.avsemprize.printful.models.Product;
import com.avsemprize.printful.models.Response;
import com.avsemprize.printful.models.info.ProductInfo;
import com.avsemprize.printful.models.info.VariantInfo;
import com.avsemprize.printful.utils.LibUtils;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * See https://www.theprintful.com/docs/products
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class ProductCatalogApiClient extends SimpleClient {

    private final static String TAG = ProductCatalogApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public ProductCatalogApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public ProductCatalogApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<Product> getAllProductList() {
        try {
            String response = LibUtils.createConnection(base64Key, "products", configuration)
                    .execute().body();
            Type type = new TypeToken<Response<Product>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
           e.printStackTrace();
        }

        return null;
    }

    @Override
    public Response<VariantInfo> getInfoAboutVariant(int variantId) {
        try {
            String response = LibUtils.createConnection(base64Key, "products/variant/" + variantId,
                    configuration).execute().body();
            Type type = new TypeToken<Response<VariantInfo>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<ProductInfo> getProductsVariantsList(int productId) {
        try {
            String response = LibUtils.createConnection(base64Key, "products/" + productId,
                    configuration).execute().body();
            Type type = new TypeToken<Response<ProductInfo>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
