========================================================================
RESTful Service
========================================================================


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
  
========================================================================

**POST**
BASEPATH : api.url which is Set in profiles/{profileName}/config.properties
ENDPOINT : Set in SampleApi.java Endpoint where the POST needs to be performed e.g "BASEPATH" + "/en/products" (Create new Api for every new service)
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

========================================================================