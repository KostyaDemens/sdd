# Veterinarian API

The application exposes a veterinarian CRUD resource at `/api/veterinarians`.

## Database configuration

The default runtime datasource is PostgreSQL. Configure it with these environment variables:

- `DATABASE_URL` (default: `jdbc:postgresql://localhost:5432/vets`)
- `DATABASE_USERNAME` (default: `postgres`)
- `DATABASE_PASSWORD` (default: `postgres`)
- `JPA_DDL_AUTO` (default: `update`)

The default schema policy creates or updates the `veterinarians` table. Once a managed migration strategy is in place, set `JPA_DDL_AUTO=validate` to require the schema to already exist.

## Endpoints

- `POST /api/veterinarians`
- `GET /api/veterinarians`
- `GET /api/veterinarians/{id}`
- `PUT /api/veterinarians/{id}`
- `DELETE /api/veterinarians/{id}`
