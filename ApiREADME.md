===============
RESTful Service
===============


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


Process Involves
Payload creation
Api creation
Step defintion creation
Feature file creation

_Payload creation_
The above Payload has a parent container with id and a collection of Items and child container as Item which has ItemType and ItemName as fields.

Create 2 models for building the payload one for parent and one for child
1 ItemsModel: - This has id field and collection/ List of items (Parent)
2 ItemModel:  Item with fields itemType and itemName (Child)

Location: src\test\java\com\salmon\test\models\api

For details of each annotation please refer to these models

_API creation_

Add Post Method


    public  static Response postDetails(List<ItemModel> itemModels) {
        ItemsModel itemsData = buildItemsData(itemModels);
        String payLoad = gson().toJson(itemsData);
        return givenConfig().
                body(payLoad).
                post(PATH);
    }


Location: src\test\java\com\salmon\test\services