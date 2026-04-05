package com.githubactivity.server.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.githubactivity.server.model.GitModel;

@Service
public class GitServiceImpl implements GitService {
    private final RestClient restClient;

    public GitServiceImpl() {
        this.restClient = RestClient.builder()
                .baseUrl("https://api.github.com")
                .defaultHeader("User-Agent", "Spring-Boot-Github-App") // CRITICAL: GitHub will block the access without
                                                                       // this
                .defaultHeader("Accept", "application/vnd.github+json")
                .build();
    }

    @Override
    public String getActivity(String username) {
        List<GitModel.GithubEvent> events = restClient.get()
                .uri("/users/{username}/events", username)
                .retrieve()
                .onStatus(status -> status.is4xxClientError(), (request, response) -> {
                    throw new RuntimeException("GitHub User not found or API limit reached.");
                })
                .body(new ParameterizedTypeReference<List<GitModel.GithubEvent>>() {
                });

        if (username == null || username.isEmpty())
            return "Error: no username inputed.";

        return events.stream()
                .limit(10)
                .map(this::formatEvent)
                .collect(Collectors.joining("\n"));
    }

    private String formatEvent(GitModel.GithubEvent event) {
        String repoName = event.repo().name();

        return switch (event.type()) {
            case "PushEvent" -> {
                int commitCount = (event.payload().commits() != null) ? event.payload().commits().size() : 0;
                String countDisplay = (commitCount > 0) ? String.valueOf(commitCount) : "some";
                yield "- Pushed " + countDisplay + " commits to " + repoName;
            }
            case "IssuesEvent" -> "- " + event.payload().action() + " a new issue in " + repoName;
            case "WatchEvent" -> "- Starred " + repoName;
            case "CreateEvent" -> "- Created " + repoName;
            default -> "- " + event.type() + " in " + repoName;
        };
    }
}