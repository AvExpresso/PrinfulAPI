package com.avsemprize.printful.clients;

import com.avsemprize.printful.SimpleClient;
import com.avsemprize.printful.models.Configuration;
import com.avsemprize.printful.models.Response;
import com.avsemprize.printful.models.info.SyncProductInfo;
import com.avsemprize.printful.utils.LibUtils;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Connection;

import java.io.IOException;
import java.lang.reflect.Type;

public class ProductsApiClient extends SimpleClient {
    private final static String TAG = ProductsApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    public ProductsApiClient(String apiKey){
        this(apiKey, new Configuration());
    }

    public ProductsApiClient(String apiKey, Configuration configuration){
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }
    public Response<SyncProductInfo> getProductInfo(Long id, String storeId){
        try{
            Connection connection = LibUtils.createConnection(base64Key, "store/products/" + id, configuration);
            connection.header("X-PF-Store-Id", storeId);
            String response = connection.execute().body();
            return createSyncProductInfo(response);

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param response body of the response obtained from API
     * @return A response object for SyncProductInfo
     */
    private Response<SyncProductInfo> createSyncProductInfo(String response) {
        Type type = new TypeToken<Response<SyncProductInfo>>() {
        }.getType();
        return LibUtils.gson.fromJson(response, type);
    }

}
