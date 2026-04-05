package com.githubactivity.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.githubactivity.server.service.GitService;

@RestController
@RequestMapping("/api/github")
public class GitController {
    private final GitService service;

    // @Autowired
    public GitController(GitService service) {
        this.service = service;
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getActivity(@PathVariable String username) {
        return ResponseEntity.ok().body(service.getActivity(username));
    }
}
