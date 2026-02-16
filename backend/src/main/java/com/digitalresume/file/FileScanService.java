package com.digitalresume.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileScanService {
    ScanResult scan(MultipartFile file);

    record ScanResult(boolean clean, String details) {}
}
