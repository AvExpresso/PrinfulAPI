package com.avsemprize.printful.clients;

import com.avsemprize.printful.SimpleClient;
import com.avsemprize.printful.models.Configuration;
import com.avsemprize.printful.models.Response;
import com.avsemprize.printful.models.includable.Country;
import com.avsemprize.printful.models.info.TaxAddressInfo;
import com.avsemprize.printful.models.info.TaxInfo;
import com.avsemprize.printful.utils.LibUtils;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;

import java.io.IOException;
import java.lang.reflect.Type;



/**
 * See https://www.theprintful.com/docs/tax
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class TaxRateApiClient extends SimpleClient {
    private final static String TAG = TaxRateApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public TaxRateApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public TaxRateApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<Country> retrieveStateListThatRequiresTaxCalculation() {
        try {
            String response = LibUtils.createConnection(base64Key, "tax/countries", configuration)
                    .execute().body();
            Type type = new TypeToken<Response<Country>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<TaxInfo> calculateTaxRate(TaxAddressInfo taxAddressInfo) {
        try {
            JsonObject recipient = new JsonObject();
            recipient.add("recipient", LibUtils.gson.toJsonTree(taxAddressInfo));
            String response = LibUtils.createConnection(base64Key, "tax/rates", configuration)
                    .method(Connection.Method.POST)
                    .requestBody(recipient.toString())
                    .execute().body();
            Type type = new TypeToken<Response<TaxInfo>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
