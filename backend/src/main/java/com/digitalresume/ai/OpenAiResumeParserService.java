package com.digitalresume.ai;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class OpenAiResumeParserService implements ResumeAiParserService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String parseResumeToJson(String plainResumeText) {
        // Placeholder implementation: replace with OpenAI API call using prompt+schema.
        try {
            return objectMapper.writeValueAsString(Map.of(
                "summary", "AI parsing pending integration",
                "rawText", plainResumeText
            ));
        } catch (Exception ex) {
            throw new IllegalStateException("Failed to construct resume JSON", ex);
        }
    }
}
