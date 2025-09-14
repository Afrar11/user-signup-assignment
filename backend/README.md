# Backend (Spring Boot)

## Run
```bash
./mvnw spring-boot:run
```
- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- H2 console: `http://localhost:8080/h2-console` (JDBC: `jdbc:h2:mem:testdb`, user `sa`)

## Endpoints
- `POST /api/auth/register`
  - Body (JSON):
    ```json
    {
      "firstName": "John",
      "lastName": "Doe",
      "email": "john@example.com",
      "password": "secret123",
      "phone": "0771234567",
      "country": "Sri Lanka",
      "roles": ["General User", "Professional"]
    }
    ```
  - Responses:
    - 201 Created (user JSON without raw password)
    - 400 Bad Request (validation errors)
    - 409 Conflict (duplicate email)

## Notes
- Passwords stored as BCrypt hashes.
- Global error handler returns clear messages.
- CORS configured for `http://localhost:3000` (edit in `CorsConfig` if needed).
