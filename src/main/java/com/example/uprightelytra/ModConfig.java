package com.example.uprightelytra;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path FILE =
            FabricLoader.getInstance().getConfigDir().resolve("uprightelytra.json");

    public static boolean enabled = true;

    public static void load() {
        if (!Files.exists(FILE)) {
            save();
            return;
        }
        try (Reader reader = Files.newBufferedReader(FILE)) {
            JsonObject obj = GSON.fromJson(reader, JsonObject.class);
            if (obj != null && obj.has("enabled")) {
                enabled = obj.get("enabled").getAsBoolean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        JsonObject obj = new JsonObject();
        obj.addProperty("enabled", enabled);
        try (Writer writer = Files.newBufferedWriter(FILE)) {
            GSON.toJson(obj, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
