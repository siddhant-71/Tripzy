package com.tripzy.Controller;

import com.tripzy.Configurations.RequiredDTO.BreifDTO;
import com.tripzy.ExternalService.AIService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AIController {

    private final AIService aiService;

    @PostMapping("/ask")
    public String getAiResponse(@RequestParam String details){
        String prompt="I want to travel to "+details+" days give me the best only one detailed unique itinerary ";
        return aiService.aiResponse(prompt);
    }
    @PostMapping("/breif")
    public String getAiResponse(@RequestBody BreifDTO details){
        String prompt="Give me the breif version of the itinerary :"+details.getContent();
        return aiService.aiResponse(prompt);
    }
}
