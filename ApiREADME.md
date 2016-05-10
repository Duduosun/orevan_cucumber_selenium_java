=======================

**RESTful Services Test Automation**

=======================

REST stands for Representational State Transfer, which is an architectural style for networked hypermedia applications, it is primarily used to build Web services that are lightweight, maintainable, and scalable

Most commonly used Rest Operations listed below with standard success  HTTP Status Codes    

| HTTP Verb  |CRUD|Success HTTP Status Codes|
| :------     |:------|:------|
|POST        |Create|201 (Created)|
| PUT  |Update/Replace|200 (OK) or 204 (No Content)|
| GET  |Read|200 (OK)|
| DELETE  |Delete|200 (OK)|
|PATCH|Update /Modify|200 (OK) or 204 (No Content)|


**Tools** specifically used for automating RESTFul services apart from core framework tools 
*Rest assured
*Gson
*Lombok
*JsonPath or XMLPath



**ApiHelper**
Framework Helper class which creates the base configuration and removes the need of boiler plate code for every tests
Location: src\test\java\com\salmon\test\framework\helpers

For serialization deserialization use gson() method, which can be used as : 
 Convert JSON to JAVA POJO /Model  : gson().fromJson()
 Convert JAVA POJO/Model to JSON  : gson().toJson()

For setting one time gson base config amend the gson() method and add extra config. Example the change is now to serialize fields in the model which are null .
        So add the extra config using gsonBuilder as gsonBuilder.serializeNulls();

public static Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        gson = gson(gsonBuilder);
        return gson;
    }
  
=======================

**POST**  

=======================


The POST verb is most-often utilized to **create** new resources. In particular, it's used to create subordinate resources. That is, subordinate to some other (e.g. parent) resource. 

BASE PATH : api.url which is Set in profiles/{profileName}/config.properties
ENDPOINT : Set in SampleApi.java Endpoint where the POST needs to be performed e.g "BASE PATH" + "/en/products" (Create new Api for every new service)
PAYLOAD : Consider the JSON payload which needs to be created for POST
```
{
	"id": "123456",
	"items":
	 [
         {
            "itemType": "TABLET",
            "itemName": "ipad"
        }, {
            "itemType": "MOBILE",
            "itemName": "iphone"
        }
	]
}
```


## Process Involves  
  *Payload creation
  *Feature file creation
  *Step definition creation
  *Api creation



#### *_Payload creation_


Understand the Data Structure before creating the Feature file
E.g The above Payload has a parent container with id and a collection of Items and child container as Item which has ItemType and ItemName as fields.

Create 2 models for building the payload one for parent and one for child
1 ItemsModel: - This has id field and collection/ List of items (Parent)
2 ItemModel:  Item with fields itemType and itemName (Child)

Location: src\test\java\com\salmon\test\models\api

For details of each annotation please refer to these models

#### *_Feature file creation_

Data can be passed via DataTable from a step of Feature file, or data can also be created within Java class
In this case lets pass multiple Items data in data Table format 

    When I create an Item
      | itemType | itemName |
      | TABLET   | ipad     |
      | MOBILE   | iphone   |

Location: src\test\resources\features

#### *_Step definition creation_

The Item Data from step definition is mapped to the collection of ItemModel created in the below step definition
This information then needs to be  passed by creating an  Api method e.g postDetails
 
@When("^I create an Item$")
    public void I_create_an_Item(List<ItemModel> items) throws Throwable {
        response = SampleApi.postDetails(items);
    }

Location: src\test\java\com\salmon\test\step_definitions\api


#### *_API creation_

  *Create POST Method: postDetails method as shown below to perform the POST operation
    Now since we just have the items data on its own, which is currently not linked to Parent Model ItemsModel. We need to build this data
  
  *Build Data:  Create buildItemsData method as shown below. 
    Since ItemsModel class has Builder method, Use this information to link the item collection data as well as add all the missing field information for parent class.
  *Payload transformation:  Convert the model data build to JSON as we want to sent this as our payload 
  *Base Configuration : use the givenConfig() method from ApiHelper.class as all Api classes extend ApiHelper.class and finally
  *POST: Finally send the transformed  payload to "body" and post to the required endpoint which is defined in PATH  


```
    public  static Response postDetails(List<ItemModel> itemModels) {
        ItemsModel itemsData = buildItemsData(itemModels);
        String payLoad = gson().toJson(itemsData);
        return givenConfig().
                body(payLoad).
                post(PATH);
    }


private static ItemsModel buildItemsData(List<ItemModel> itemModels) {
        return ItemsModel.builder().id("123456").
                items(itemModels).
                build();
    }
```

Location: src\test\java\com\salmon\test\services

=======================

**PUT**  

=======================


Exactly same technique to be used as POST request. PUT is typically use for update operation
Only difference is RESTful operation type is PUT 

```
public static Response updateDetails(List<ItemModel> itemModels) {
        ItemsModel itemsData = buildItemsData(itemModels);
        String payLoad = gson().toJson(itemsData);
        return givenConfig().
                body(payLoad).
                when().
                put(PATH);
    }
```    
 
 
=======================

**DELETE**  

=======================


Delete a resource identified by a URI.

BASE PATH : api.url which is Set in profiles/{profileName}/config.properties
ENDPOINT : Set in SampleApi.java Endpoint where the DELETE operation needs to be performed e.g "BASE PATH" + "/en/products" or with uniqueID "BASE PATH" + "/en/products/{uniqueID}" 
PAYLOAD : NA

 
  *Identify Resource to be deleted
  *Feature file creation
  *Step definition creation
  *Api creation

#### *_Identify Resource to be deleted_

Analyse whether a single resource needs to be deleted or collection or resource to be deleted by uniqueID
For resource to be deleted by UniqueID, the UniqueID needs to be found e.g either by previous GET Request etc...

#### *_Feature file creation_

Refer to framework example

#### *_Step definition creation_

Refer to framework example

#### *_API creation_

  *Base Configuration : use the givenConfig() method from ApiHelper.class as all Api classes extend ApiHelper.class and finally
  *DELETE: Finally perform delete operation for required endpoint which is defined in PATH with our without uniqueId


```
public static Response deleteItem(String uniqueId) {
        return givenConfig().
                when().delete(PATH + uniqueId);
    }
```   
  
  
  
=======================

  **GET**  
  
=======================


 The HTTP GET method is used to read (or retrieve) a representation of a resource. 
 
  BASE PATH : api.url which is Set in profiles/{profileName}/config.properties
  ENDPOINT : Set in SampleApi.java Endpoint where the GET operation needs to be performed e.g "BASE PATH" + "/en/products" or  with uniqueID "BASE PATH" + "/en/products/{uniqueID}" 
  PAYLOAD : NA


## Process Involves
   
    *Identify Resource to be retrieved
    *Feature file creation
    *Step definition creation
    *Api creation
  
#### *_Identify Resource to be retrieved_
  
  Analyse whether a single resource needs to be retrieved or collection or resource to be deleted by uniqueID
  For resource to be retrieved by UniqueID, the UniqueID needs to be found
  
  
#### *_Feature file creation_
  
  Refer to framework example
  
#### *_Step definition creation_
  
  Refer to framework example
  
#### *_API creation_
  
   *Base Configuration : use the givenConfig() method from ApiHelper.class as all Api classes extend ApiHelper.class and finally
   *GET: Finally perform get operation to retrieve a single or collection of resources


    
        public static final String COLOURS_PATH = "/en/api/products/colors";
        
        public static Response getListOfColours() {
            return givenConfig().when().get(COLOURS_PATH);
        }
       
    
    
=======================

**Response Mapping and Validation**  

=======================


The success of RESTful Operation is finally validated with the Response Object returned 

Common Response validation
*Status Code
*Fields validation of returned PayLoad via De-serializing


#### *_Status Code_

```
int statusCode = response.getStatusCode();
        switch (result) {
            case "created":
                assertThat(statusCode).isEqualTo(201);
                break;
            case "updated":
                assertThat(statusCode).isEqualTo(200);
                break;
            case "deleted":
                assertThat(statusCode).isEqualTo(204);
                break;
        }
```


#### *_Fields validation of returned PayLoad via De-serializing_

 

_Simple Example with JsonPath to extract name of colours from "response object" and store in List_

        List<String> colourNames = from(response.asString()).get("colors.name");
        
        //Validation for colourNames is not empty
        assertThat(colourNames.size()).isGreaterThan(0);

_Example with JsonPath to extract colours from "response object" and store in List of COLOUR OBJECTS_
  
        List<ResponseModel.Colors> colors = from(response.asString()).get("colors");
        
         //Validation for colour object collection is not empty
          assertThat(colors.size()).isGreaterThan(0);


_Example with GSON to deserialize entire PayLoad from "response object" and store as a Response Object_
          
          Gson gson = SampleApi.gson();
          ResponseModel responseModel = gson.fromJson(response.asString(), ResponseModel.class);
        
         //Validation for colour object collection is not empty
          assertThat(responseModel.getColors().size()).isGreaterThan(0);
