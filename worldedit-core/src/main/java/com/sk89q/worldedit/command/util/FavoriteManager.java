package com.sk89q.worldedit.command.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FavoriteManager {

    private static final String FAVORITES_FILE = "favorites.json";  // Caminho do arquivo JSON para armazenar favoritos
    private Map<String, Set<String>> favoritesPerPlayer;

    public FavoriteManager() {
        favoritesPerPlayer = new HashMap<>();
        loadFavorites();  // Carregar favoritos ao iniciar a classe
    }

    public void addFavorite(String playerName, String schematicName) {
        favoritesPerPlayer.putIfAbsent(playerName, new HashSet<>());
        Set<String> userFavorites = favoritesPerPlayer.get(playerName);
        userFavorites.add(schematicName);
        saveFavorites();  // Salvar favoritos sempre que for atualizado
    }

    public void removeFavorite(String playerName, String schematicName) {
        Set<String> userFavorites = favoritesPerPlayer.get(playerName);
        if (userFavorites != null) {
            userFavorites.remove(schematicName);
            saveFavorites();  // Salvar favoritos sempre que for atualizado
        }
    }

    public Set<String> getFavorites(String playerName) {
        return favoritesPerPlayer.getOrDefault(playerName, new HashSet<>());
    }

    // Salva os favoritos no arquivo
    private void saveFavorites() {
        try (FileWriter writer = new FileWriter(FAVORITES_FILE)) {
            Gson gson = new Gson();
            gson.toJson(favoritesPerPlayer, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carrega os favoritos a partir do arquivo
    private void loadFavorites() {
        Path filePath = Paths.get(FAVORITES_FILE);
        if (Files.exists(filePath)) {
            try (Reader reader = Files.newBufferedReader(filePath)) {
                Gson gson = new Gson();
                favoritesPerPlayer = gson.fromJson(reader, new TypeToken<Map<String, Set<String>>>(){}.getType());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
