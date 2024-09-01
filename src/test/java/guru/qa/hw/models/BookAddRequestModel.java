package guru.qa.hw.models;

import lombok.Data;

import java.util.List;

@Data
public class BookAddRequestModel {
    private String userId;
    private List<IsbmModel> collectionOfIsbns;
}