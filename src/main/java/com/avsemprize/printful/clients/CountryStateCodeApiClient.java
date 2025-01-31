package com.avsemprize.printful.clients;

import com.avsemprize.printful.SimpleClient;
import com.avsemprize.printful.models.Configuration;
import com.avsemprize.printful.models.Response;
import com.avsemprize.printful.models.includable.Country;
import com.avsemprize.printful.utils.LibUtils;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;



/**
 * See https://www.theprintful.com/docs/countries
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class CountryStateCodeApiClient extends SimpleClient {
    private final static String TAG = CountryStateCodeApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public CountryStateCodeApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public CountryStateCodeApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<Country> retrieveCountryList() {
        try {
            String response = LibUtils.createConnection(base64Key, "countries", configuration).execute().body();
            Type type = new TypeToken<Response<Country>>() {
            }.getType();
            return LibUtils.gson.fromJson(response, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
