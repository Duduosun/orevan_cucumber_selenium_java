package com.salmon.test.models.api;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;


// Container model for Item Model
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder // Reduces massive vanilla code of Builder implementation Java
@Data
public class ItemsModel {
    String id;
    List<ItemModel> items;
}
