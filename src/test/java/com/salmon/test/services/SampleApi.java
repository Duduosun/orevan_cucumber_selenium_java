package com.salmon.test.services;

import com.jayway.restassured.response.Response;
import com.salmon.test.framework.helpers.ApiHelper;
import com.salmon.test.models.api.ItemModel;
import com.salmon.test.models.api.ItemsModel;

import java.util.List;

public class SampleApi extends ApiHelper {

    public static final String PATH = "items/";
    public static final String COLOURS_PATH = "/en/api/products/colors";

    public static Response getListOfColours() {
        return givenConfig().when().get(COLOURS_PATH);
    }

    public static Response postDetails(List<ItemModel> itemModels) {
        ItemsModel itemsData = buildItemsData(itemModels);
        String payLoad = gson().toJson(itemsData);
        return givenConfig().
                body(payLoad).
                //If there are Query Params in URL
                //queryParam("").
                //If there are Form Params in URL
                //formParam("").
                        post(PATH);
    }

    private static ItemsModel buildItemsData(List<ItemModel> itemModels) {
        return ItemsModel.builder().id("123456").
                items(itemModels).
                build();
    }

    public static Response updateDetails(List<ItemModel> itemModels) {
        ItemsModel itemsData = buildItemsData(itemModels);
        String payLoad = gson().toJson(itemsData);
        return givenConfig().
                body(payLoad).
                when().
                put(PATH);
    }


    public static Response deleteItem(String uniqueId) {
        return givenConfig().
                when().delete(PATH + uniqueId);
    }

}
