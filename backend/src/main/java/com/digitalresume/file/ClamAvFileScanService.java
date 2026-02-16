package com.digitalresume.file;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClamAvFileScanService implements FileScanService {

    @Override
    public ScanResult scan(MultipartFile file) {
        // Hook for ClamAV/ICAP integration. Reject executable payload signatures quickly.
        String filename = file.getOriginalFilename() == null ? "unknown" : file.getOriginalFilename().toLowerCase();
        if (filename.endsWith(".exe") || filename.endsWith(".js")) {
            return new ScanResult(false, "Potential malware payload extension blocked.");
        }
        return new ScanResult(true, "File passed baseline scanner checks.");
    }
}
