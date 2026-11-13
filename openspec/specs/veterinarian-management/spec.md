# veterinarian-management Specification

## Purpose
Allow API clients to maintain an accurate, persistent directory of veterinarians and their contact details.

## Requirements

### Requirement: Veterinarian record data
The system SHALL persist each veterinarian with a generated identifier, full name, specialization, phone number, and email address. Full name, specialization, phone number, and email address MUST be non-blank; email address MUST use a valid email format.

#### Scenario: Valid veterinarian data is accepted
- **WHEN** a client submits all required fields with a syntactically valid email address
- **THEN** the system accepts the veterinarian data for creation or update

#### Scenario: Invalid veterinarian data is rejected
- **WHEN** a client submits a blank required field or an invalid email address
- **THEN** the system responds with HTTP 400 and validation-error details

### Requirement: Create veterinarian
The system SHALL create a veterinarian from valid request data through `POST /api/veterinarians` and SHALL return the created record with its generated identifier.

#### Scenario: Veterinarian is created
- **WHEN** a client sends valid veterinarian data to `POST /api/veterinarians`
- **THEN** the system persists the record and responds with HTTP 201 and the created veterinarian representation

### Requirement: Retrieve veterinarians
The system SHALL expose `GET /api/veterinarians` to retrieve all veterinarian records and `GET /api/veterinarians/{id}` to retrieve a single record by identifier.

#### Scenario: Veterinarian collection is retrieved
- **WHEN** a client requests `GET /api/veterinarians`
- **THEN** the system responds with HTTP 200 and a JSON array of veterinarian records

#### Scenario: Veterinarian is retrieved by identifier
- **WHEN** a client requests `GET /api/veterinarians/{id}` for an existing record
- **THEN** the system responds with HTTP 200 and that veterinarian representation

#### Scenario: Missing veterinarian is requested
- **WHEN** a client requests `GET /api/veterinarians/{id}` for a nonexistent identifier
- **THEN** the system responds with HTTP 404

### Requirement: Update veterinarian
The system SHALL replace a veterinarian's editable data through `PUT /api/veterinarians/{id}` when the request data is valid.

#### Scenario: Veterinarian is updated
- **WHEN** a client sends valid veterinarian data to `PUT /api/veterinarians/{id}` for an existing record
- **THEN** the system persists the changed data and responds with HTTP 200 and the updated veterinarian representation

#### Scenario: Missing veterinarian is updated
- **WHEN** a client sends `PUT /api/veterinarians/{id}` for a nonexistent identifier
- **THEN** the system responds with HTTP 404

### Requirement: Delete veterinarian
The system SHALL delete a veterinarian through `DELETE /api/veterinarians/{id}`.

#### Scenario: Veterinarian is deleted
- **WHEN** a client sends `DELETE /api/veterinarians/{id}` for an existing record
- **THEN** the system removes the record and responds with HTTP 204

#### Scenario: Missing veterinarian is deleted
- **WHEN** a client sends `DELETE /api/veterinarians/{id}` for a nonexistent identifier
- **THEN** the system responds with HTTP 404
