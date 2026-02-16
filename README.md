# Digital Resume Platform

Full-stack starter implementation for a role-based digital resume platform.

## Included modules
- `frontend/`: Angular 17 standalone application with:
  - Email/password login and Google login redirect.
  - Candidate home page with resume upload flow.
  - Digital resume layout with top navigation, profile image controls, collapsible education and experience sections, contact section and inline edit actions.
  - Recruiter candidate search screen with sortable/listable columns.
  - Admin user management view.
- `backend/`: Spring Boot 3.3 service with:
  - OAuth2-ready security configuration.
  - User, resume, admin and recruiter APIs.
  - Resume upload endpoint with malware scan hook and AI parsing abstraction.
  - PostgreSQL JPA entities/repositories.

## Backend API highlights
- `POST /api/auth/register`
- `POST /api/auth/logout`
- `POST /api/resumes/upload`
- `GET /api/resumes/{userId}`
- `GET /api/recruiter/candidates`
- `PATCH /api/admin/users/{userId}/status`
- `PATCH /api/admin/users/{userId}/roles`
- `DELETE /api/admin/users/{userId}`

## Security and processing flow
1. Candidate uploads resume.
2. `FileScanService` scans for suspicious payloads before storage.
3. Clean files are persisted under `app.upload.path`.
4. Resume text is sent to `ResumeAiParserService` (OpenAI integration point) to generate structured JSON.
5. JSON is saved and returned directly to Angular for rendering/editing.

## Next steps for production
- Replace placeholder OpenAI parser with secure API integration and response schema validation.
- Replace baseline file scanner with ClamAV/ICAP daemon integration.
- Add migration scripts (Flyway/Liquibase), DTO mappers, robust validation and audit logs.
- Add JWT issuing authorization server and refresh token support.
- Add Angular HTTP services, state management and route guards.
