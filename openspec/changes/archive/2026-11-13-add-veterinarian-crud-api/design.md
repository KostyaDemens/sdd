# Design

## Context

The project is a minimal Java 21 Spring Boot 4.1 WAR application. It already includes Spring Web MVC, Spring Data JPA, Bean Validation, PostgreSQL's JDBC driver, and test support, but contains only the application bootstrap and no domain, persistence, or API layers. See `proposal.md` for motivation and `specs/veterinarian-management/spec.md` for the behavior contract.

## Goals / Non-Goals

**Goals:**

- Add one independently managed persistent resource with a conventional JSON REST interface.
- Keep transport validation separate from persistence and centralize HTTP error mapping.
- Establish a package structure that can grow with additional domain resources.

**Non-Goals:**

- Authentication, authorization, pagination, filtering, sorting, search, audit history, and soft deletion.
- Database migration tooling or a production database deployment configuration.
- Relationships to animals, appointments, clinics, or other future entities.

## Decisions

### Layered resource implementation

Implement a JPA `Veterinarian` entity, a Spring Data repository, a service owning lookup and mutation rules, request/response DTOs, and a REST controller at `/api/veterinarians`. This keeps HTTP representation and validation from leaking into persistence while giving future use cases a clear layer for business rules.

Alternative: expose the JPA entity directly from a controller. Rejected because it couples the public contract to schema details and makes validation and future API evolution harder.

### Package layout and boilerplate

Use a layered package structure: `controller`, `service`, `repository`, `dto`, `entity`, and `mapper`. Place each class in the layer that owns its responsibility, and use a mapper to translate between entities and DTOs. Use Lombok for DTOs and entities when the generated code does not reduce readability; keep explicit code where it makes behavior or intent clearer.

Alternative: keep all veterinarian classes in a single feature package and write all accessors manually. Rejected because the layered layout makes dependencies and responsibilities clearer as the API grows, while selective Lombok use removes routine boilerplate without obscuring the model.

### Generated numeric identifier and full replacement updates

Use a generated numeric primary key for the database record. `PUT /api/veterinarians/{id}` accepts the complete editable representation and replaces those fields; the identifier remains immutable. This matches the requested CRUD semantics and makes update validation identical to creation validation.

Alternative: client-provided identifiers or partial `PATCH` updates. Rejected because neither is required and each introduces additional validation and compatibility rules.

### Bean Validation at the API boundary

Use Bean Validation on create/update request DTOs: non-blank name, specialization, and phone; valid, non-blank email. Translate validation failures into a consistent HTTP 400 response. Map missing entity lookups to HTTP 404 through centralized exception handling.

Alternative: validate only in the service or database. Rejected because clients need predictable, immediate HTTP validation responses and database-only constraints do not provide field-level feedback.

### Database schema initialization follows the configured environment

Map the entity with JPA and rely on the active Spring/JPA configuration to initialize or validate schema. The current repository has the PostgreSQL driver but no datasource configuration, so implementation must provide an environment-specific connection and schema strategy before an application instance can persist records.

Alternative: add a migration framework in this change. Rejected because it expands the request beyond one resource; migrations can be adopted coherently when database lifecycle requirements are established.

## Risks / Trade-offs

- [No datasource is configured] → Document and provide the required local/runtime datasource settings during implementation; persistence integration tests must use an isolated test database or configured substitute.
- [Returning all records is unbounded] → Keep the first version simple as scoped; add pagination before the resource is expected to grow large.
- [Contact data can be malformed beyond basic validation] → Enforce non-blank fields and email syntax now; add domain-specific phone normalization only when a business format is defined.

## Migration Plan

1. Deploy the application with a configured database and schema-initialization policy that creates or migrates the veterinarian table.
2. Release the new endpoints; no existing API or data is modified.
3. Roll back application code if necessary. The added table is independent and may remain without affecting earlier application behavior.
