package com.joelmaciel.authservice.api.security;

import com.joelmaciel.authservice.api.dtos.RequestDTO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDTO> paths;

    public List<RequestDTO> getPaths() {
        return paths;
    }

    public void setPaths(List<RequestDTO> paths) {
        this.paths = paths;
    }

    public boolean isAdmin(RequestDTO requestDto){
        return paths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(),requestDto.getUri()) && p.getMethod().equals(requestDto.getMethod()));
    }
}
