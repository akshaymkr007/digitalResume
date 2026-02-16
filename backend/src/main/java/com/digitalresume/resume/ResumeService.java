package com.digitalresume.resume;

import com.digitalresume.ai.ResumeAiParserService;
import com.digitalresume.file.FileScanService;
import com.digitalresume.user.UserEntity;
import com.digitalresume.user.UserRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final FileScanService fileScanService;
    private final ResumeAiParserService resumeAiParserService;
    private final Path uploadPath;

    public ResumeService(
        ResumeRepository resumeRepository,
        UserRepository userRepository,
        FileScanService fileScanService,
        ResumeAiParserService resumeAiParserService,
        @Value("${app.upload.path:uploads}") String uploadDir) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
        this.fileScanService = fileScanService;
        this.resumeAiParserService = resumeAiParserService;
        this.uploadPath = Path.of(uploadDir);
    }

    public ResumeEntity uploadAndParse(Long userId, MultipartFile file) throws IOException {
        FileScanService.ScanResult scanResult = fileScanService.scan(file);
        if (!scanResult.clean()) {
            throw new IllegalArgumentException("File scanning failed: " + scanResult.details());
        }

        Files.createDirectories(uploadPath);
        Path savedFile = uploadPath.resolve(userId + "-" + file.getOriginalFilename());
        Files.write(savedFile, file.getBytes());

        String parsedJson = resumeAiParserService.parseResumeToJson(new String(file.getBytes()));
        UserEntity user = userRepository.findById(userId).orElseThrow();
        ResumeEntity resume = resumeRepository.findTopByUserOrderByUpdatedAtDesc(user).orElseGet(ResumeEntity::new);
        resume.setUser(user);
        resume.setSourceFilePath(savedFile.toString());
        resume.setParsedResumeJson(parsedJson);
        resume.setUpdatedAt(Instant.now());
        return resumeRepository.save(resume);
    }

    public ResumeEntity getCurrentResume(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow();
        return resumeRepository.findTopByUserOrderByUpdatedAtDesc(user).orElseThrow();
    }
}
