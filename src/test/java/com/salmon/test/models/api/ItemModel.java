package com.salmon.test.models.api;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE) // Removes necessity to specify access modifier for every fields
@Data //  @Getter / @Setter and @RequiredArgsConstructor, @ToString, @EqualsAndHashCode,
public class ItemModel {
    String itemId;
    String itemType;
    String itemName;
    String itemDescription;
}
