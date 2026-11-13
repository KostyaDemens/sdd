# Tasks

## 1. Persistence and application configuration

- [x] 1.1 Add the `Veterinarian` JPA entity with generated identifier and required contact fields, plus the Spring Data repository; verify the application maps the entity and repository with a configured test datasource.
- [x] 1.2 Provide documented datasource and JPA schema settings needed to run the resource against PostgreSQL; verify the application starts with those settings and creates or validates the veterinarian table.

## 2. Veterinarian CRUD API

- [x] 2.1 Add validated create/update request DTOs and a response DTO, including required-field and email validation; verify invalid payload tests return HTTP 400 with validation details.
- [x] 2.2 Implement the service layer for creation, collection and single-record retrieval, full replacement update, and deletion; verify service tests cover successful operations and nonexistent identifiers.
- [x] 2.3 Add `/api/veterinarians` REST endpoints for POST, GET collection, GET by ID, PUT by ID, and DELETE by ID; verify endpoint tests assert HTTP 201, 200, 204, and returned JSON representations.
- [x] 2.4 Add centralized handling for missing veterinarian records; verify GET, PUT, and DELETE requests for nonexistent identifiers return HTTP 404.
- [x] 2.5 Reorganize the veterinarian implementation into `controller`, `service`, `repository`, `dto`, `entity`, and `mapper` packages; add entity/DTO mapping and use Lombok selectively for DTOs and entities without reducing readability; update affected tests and verify the CRUD API still passes.

## 3. Integration verification

- [x] 3.1 Add an end-to-end CRUD API test using an isolated persistence configuration; verify it creates, retrieves, updates, deletes, and then receives HTTP 404 for the removed record.
- [x] 3.2 Run the Maven test suite; verify `./mvnw test` completes successfully.

## Workflow follow-up

- Archive the change after implementation and project review are complete.
- Verify the archived result and synced specifications.
