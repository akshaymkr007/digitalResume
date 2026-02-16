package com.digitalresume.resume;

public class ResumeDtos {
    public record ResumeResponse(Long id, Long userId, String sourceFilePath, String parsedResumeJson) {}
}
