package org.isep.cleancode.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class AppConfig {
    public enum RepoType { INMEMORY, CSV }

    private final RepoType repoType;

    public AppConfig(String configPath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configPath)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Impossible de charger la config : " + configPath, e);
        }
        String repo = props.getProperty("repository", "INMEMORY").toUpperCase();
        try {
            this.repoType = RepoType.valueOf(repo);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Repository invalide dans config : " + repo, e);
        }
    }

    public RepoType getRepoType() {
        return repoType;
    }
}