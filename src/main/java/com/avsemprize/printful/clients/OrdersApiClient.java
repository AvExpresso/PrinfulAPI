package com.avsemprize.printful.clients;

import com.avsemprize.printful.SimpleClient;
import com.avsemprize.printful.enums.OrderStatus;
import com.avsemprize.printful.models.Configuration;
import com.avsemprize.printful.models.Order;
import com.avsemprize.printful.models.Response;
import com.avsemprize.printful.utils.LibUtils;
import com.google.gson.reflect.TypeToken;

import org.jsoup.Connection;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Locale;

/**
 * See https://www.theprintful.com/docs/orders
 * <p>
 *
 * @author shuklaalok7
 * @since 24/12/2016
 */
public class OrdersApiClient extends SimpleClient {

    private final static String TAG = OrdersApiClient.class.getSimpleName();

    private final String base64Key;
    private Configuration configuration;

    /**
     * @param apiKey
     */
    public OrdersApiClient(String apiKey) {
        this(apiKey, new Configuration());
    }

    /**
     * @param apiKey
     * @param configuration
     */
    public OrdersApiClient(String apiKey, Configuration configuration) {
        this.base64Key = LibUtils.encodeToBase64(apiKey);
        this.configuration = configuration;
    }

    @Override
    public Response<Order> getListOfOrders(OrderStatus status, int offset, int limit) {
        try {
            Connection connection = LibUtils.createConnection(base64Key, "orders", configuration);

            if (status != null) {
                connection.data("status", status.toString());
            }

            if (offset > 0) {
                connection.data("offset", String.valueOf(offset));
            }

            if (limit > 0) {
                if (limit > 100) {
                    limit = 100;
                }
                connection.data("limit", String.valueOf(limit));
            }

            return createResponseFromApi(connection.execute().body());
        } catch (IOException e) {
           e.printStackTrace();
        }

        return null;
    }

    @Override
    public Response<Order> createANewOrder(Order order, boolean confirm, boolean updateExisting) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }

        try {
            String response = LibUtils.createConnection(base64Key, "orders", configuration)
                    .method(Connection.Method.POST).requestBody(LibUtils.gson.toJson(order))
                    .data("confirm", String.valueOf(confirm),
                            "update_existing", String.valueOf(updateExisting))
                    .execute().body();

            return createResponseFromApi(response);
        } catch (IOException e) {
            try {
               e.printStackTrace();
            } catch (Exception e1) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public Response<Order> getOrderData(int orderId) {
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + orderId, configuration).execute().body();
            return createResponseFromApi(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<Order> getOrderData(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);

        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + externalId, configuration).execute().body();
            return createResponseFromApi(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<Order> cancelAnOrder(int orderId) {
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + orderId, configuration)
                    .method(Connection.Method.DELETE).execute().body();
            return createResponseFromApi(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<Order> cancelAnOrder(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + externalId, configuration)
                    .method(Connection.Method.DELETE).execute().body();
            return createResponseFromApi(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<Order> updateOrderData(int orderId, Order order, boolean confirm) {
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + orderId, configuration)
                    .method(Connection.Method.PUT)
                    .requestBody(LibUtils.gson.toJson(order))
                    .data("confirm", String.valueOf(confirm))
                    .execute().body();
            return createResponseFromApi(response);
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<Order> updateOrderData(String externalId, Order order, boolean confirm) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key, "orders/" + externalId, configuration)
                    .method(Connection.Method.PUT)
                    .requestBody(LibUtils.gson.toJson(order))
                    .data("confirm", String.valueOf(confirm))
                    .execute().body();
            return createResponseFromApi(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(int orderId) {
        try {
            String response = LibUtils.createConnection(base64Key,
                    String.format(Locale.ENGLISH, "orders/%d/confirm", orderId), configuration)
                    .method(Connection.Method.POST).execute().body();
            return createResponseFromApi(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<Order> confirmDraftForFulfillment(String externalId) {
        externalId = LibUtils.curateExternalId(externalId);
        try {
            String response = LibUtils.createConnection(base64Key,
                    String.format(Locale.ENGLISH, "orders/%s/confirm", externalId), configuration)
                    .method(Connection.Method.POST).execute().body();
            return createResponseFromApi(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param response The response obtained from API
     * @return Response (Order) object where result contains a singleton list
     */
    private Response<Order> createResponseFromApi(String response) {
        Type type = new TypeToken<Response<Order>>() {
        }.getType();
        return LibUtils.gson.fromJson(response, type);
    }

}
