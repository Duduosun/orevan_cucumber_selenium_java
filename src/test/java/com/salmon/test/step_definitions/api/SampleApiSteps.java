package com.salmon.test.step_definitions.api;

import com.google.gson.Gson;
import com.jayway.restassured.response.Response;
import com.salmon.test.models.api.ItemModel;
import com.salmon.test.models.api.ResponseModel;
import com.salmon.test.services.SampleApi;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

import static com.jayway.restassured.path.json.JsonPath.from;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */

public class SampleApiSteps {

    private Response response;


    /*   Perform a HTTP GET request for an endpoint*/
    @When("^I get the list of colours$")
    public void iGetTheListOfColours() throws Throwable {
        response = SampleApi.getListOfColours();
    }


    /* Example with JsonPath to extract names of colour form JSON response

    * Convert Response Object to asString(), which is Json Representation
    * use JsonPath "from" to convert the Response Object to Json String Representation
    * Access converted JSON String representation using locator e.g "colors.name"
    *  Example with XmlPath
    * String xml = post("/shopping").andReturn().body().asString()
    * Node category = from(xml).get("shopping.category[0]");

    */

    @Then("^the colour collections contain colour name$")
    public void the_colour_collections_contain_colour_name() {

        //Example with simple JsonPath
        List<String> colourNames = from(response.asString()).get("colors.name");
        assertThat(colourNames.size()).isGreaterThan(0);

        List<ResponseModel.Colors> colors = from(response.asString()).get("colors");

        assertThat(colors.size()).isGreaterThan(0);

        Gson gson = SampleApi.gson();
        ResponseModel responseModel = gson.fromJson(response.asString(), ResponseModel.class);
        assertThat(responseModel.getColors().size()).isGreaterThan(0);

    }


    @When("^I create an Item$")
    public void I_create_an_Item(List<ItemModel> items) throws Throwable {
        response = SampleApi.postDetails(items);
    }


    @When("^I update an Item$")
    public void I_update_an_Item(List<ItemModel> items) throws Throwable {
        response = SampleApi.updateDetails(items);
    }

    @When("^I delete an Item \"([^\"]*)\"$")
    public void I_delete_an_Item(String uniqueId) throws Throwable {
        response = SampleApi.deleteItem(uniqueId);
    }

    /*   Verify HTTP Status code from response*/
    @Then("^the (?:Item is|Items are) \"([^\"]*)\"$")
    public void the_Item_is(String result) throws Throwable {
        int statusCode = response.getStatusCode();
        switch (result) {
            case ("created"):

                assertThat(statusCode).isEqualTo(201);
                break;
            case "updated":
            case ("retrieved"):
                assertThat(statusCode).isEqualTo(200);
                break;
            case "deleted":
                assertThat(statusCode).isEqualTo(204);
                break;
        }

    }


}