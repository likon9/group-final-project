package data.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonService <T> {
    private static final ObjectMapper mapper = new ObjectMapper();

    public void writeJson(String filePath, List<T> collection) throws IOException {
        try {
            mapper.writeValue(new File(filePath), collection);
            mapper.writerWithDefaultPrettyPrinter().writeValueAsString(collection);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<T> readJson(String filePath, Class<T> valueType) {
        List<T> deserializedItems = new ArrayList<>();
        try {
            deserializedItems = mapper.readValue(
                    new File(filePath),
                    mapper.getTypeFactory().constructCollectionType(List.class, valueType));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return deserializedItems;
    }
}
