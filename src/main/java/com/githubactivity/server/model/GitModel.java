package com.githubactivity.server.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GitModel {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record GithubEvent(
            String type,
            Repo repo,
            Payload payload) {
        public record Repo(String name) {
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public record Payload(
                @JsonProperty("commits") java.util.List<Map<String, Object>> commits,
                String action) {
        }
    }
}
