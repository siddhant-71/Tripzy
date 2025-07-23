package com.tripzy.ExternalService;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Autowired
    private WebClient webClient;
    private final String apiKey="AIzaSyA5JkSD3wzb1PJiwwnRF53PpwTX3hRs2Sk";
    public String aiResponse(String prompt){

        String fullUrl="https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key="+apiKey;

        Map<String,Object> requestBody=Map.of("contents",List.of(Map.of("parts", List.of(Map.of("text",prompt)))));


        JsonNode node=webClient.post()
                .uri(fullUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        JsonNode ans=node.get("candidates").get(0).get("content").get("parts").get(0).get("text");

        return ans.asText() ;
    }
}
