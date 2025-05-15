package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;

public class FileStorage {
    private static final Gson gson = new Gson();

    public static <T> void saveToFile(List<T> data, String filePath) {//Saves a list of Java objects to a file in JSON format
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        } catch (Exception e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public static <T> List<T> loadFromFile(String filePath, Type typeOfT) {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, typeOfT);
        } catch (Exception e) {
            System.out.println("Error loading from file: " + e.getMessage());
            return null;
        }
    }
}
