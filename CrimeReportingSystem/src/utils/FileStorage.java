
package utils;

import com.google.gson.*;
import models.*;
import utils.typeadapters.RuntimeTypeAdapterFactory;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.List;

public class FileStorage {
    private static final RuntimeTypeAdapterFactory<User> userAdapterFactory = RuntimeTypeAdapterFactory
            .of(User.class, "role")
            .registerSubtype(Citizen.class, "citizen")
            .registerSubtype(Admin.class, "admin")
            .registerSubtype(Police.class, "police");

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonSerializer<LocalDateTime>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
            .registerTypeAdapter(LocalDateTime.class,
                    (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) -> LocalDateTime.parse(json.getAsString()))
            .registerTypeAdapterFactory(userAdapterFactory)
            .create();

    public static <T> void saveToFile(List<T> data, String filePath) {
        try {
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (FileWriter writer = new FileWriter(file)) {
                gson.toJson(data, writer);
            }
        } catch (Exception e) {
            System.out.println(" Error saving to file: " + filePath);
            System.out.println("Reason: " + e.getMessage());
        }
    }

    public static <T> List<T> loadFromFile(String filePath, Type typeOfT) {
        try {
            File file = new File(filePath);
            if (!file.exists()) return null;

            try (FileReader reader = new FileReader(file)) {
                return gson.fromJson(reader, typeOfT);
            }
        } catch (Exception e) {
            System.out.println("Error loading from file: " + filePath);
            System.out.println("Reason: " + e.getMessage());
            return null;
        }
    }

    public static void writeToFile(File file, String text) {
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(text);
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFromFile(File file) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(" Error reading from file: " + file.getAbsolutePath());
            System.out.println("Reason: " + e.getMessage());
        }
        return content.toString();
    }
}


