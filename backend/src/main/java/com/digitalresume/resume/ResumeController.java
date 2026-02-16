package com.digitalresume.resume;

import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResumeDtos.ResumeResponse> upload(@RequestParam Long userId, @RequestParam MultipartFile file)
        throws IOException {
        ResumeEntity resume = resumeService.uploadAndParse(userId, file);
        return ResponseEntity.ok(new ResumeDtos.ResumeResponse(
            resume.getId(),
            resume.getUser().getId(),
            resume.getSourceFilePath(),
            resume.getParsedResumeJson()));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResumeDtos.ResumeResponse> getCurrentResume(@PathVariable Long userId) {
        ResumeEntity resume = resumeService.getCurrentResume(userId);
        return ResponseEntity.ok(new ResumeDtos.ResumeResponse(
            resume.getId(),
            resume.getUser().getId(),
            resume.getSourceFilePath(),
            resume.getParsedResumeJson()));
    }
}
