package com.digitalresume.recruiter;

import com.digitalresume.resume.ResumeRepository;
import com.digitalresume.user.UserEntity;
import com.digitalresume.user.UserRepository;
import com.digitalresume.user.UserRole;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recruiter/candidates")
public class CandidateSearchController {

    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;

    public CandidateSearchController(UserRepository userRepository, ResumeRepository resumeRepository) {
        this.userRepository = userRepository;
        this.resumeRepository = resumeRepository;
    }

    @GetMapping
    public ResponseEntity<Page<CandidateView>> search(
        @RequestParam(defaultValue = "") String query,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "firstName") String sortBy,
        @RequestParam(defaultValue = "ASC") Sort.Direction direction) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        List<CandidateView> filtered = userRepository.findAll(Sort.by(direction, sortBy)).stream()
            .filter(user -> user.getRoles().contains(UserRole.CANDIDATE))
            .map(this::toView)
            .filter(view -> view.fullName().toLowerCase().contains(query.toLowerCase())
                || view.location().toLowerCase().contains(query.toLowerCase())
                || view.profession().toLowerCase().contains(query.toLowerCase()))
            .toList();

        int start = Math.min((int) pageable.getOffset(), filtered.size());
        int end = Math.min(start + pageable.getPageSize(), filtered.size());
        return ResponseEntity.ok(new PageImpl<>(filtered.subList(start, end), pageable, filtered.size()));
    }

    private CandidateView toView(UserEntity user) {
        String profession = resumeRepository.findTopByUserOrderByUpdatedAtDesc(user)
            .map(resume -> "Resume Available")
            .orElse("Not Uploaded");
        return new CandidateView(
            user.getId(),
            user.getFirstName() + " " + user.getLastName(),
            user.getLocation() == null ? "N/A" : user.getLocation(),
            profession,
            "N/A",
            "N/A");
    }

    public record CandidateView(Long userId, String fullName, String location, String profession, String highestQualification, String experience) {}
}
