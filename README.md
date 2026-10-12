# Tata Interview - Microservicios

Proyecto de arquitectura de microservicios basado en Spring Boot 3 / Java 21, RabbitMQ y PostgreSQL.

## Microservicios

- **customer-service (`customer-service`)**: Gestión de clientes y personas. Publica eventos de dominio ante cambios de clientes.
- **account-service (`account-service`)**: Gestión de cuentas bancarias, movimientos y generación de reportes de estado de cuenta. Mantiene consistencia eventual y réplicas mediante colas.

---

## Despliegue con Docker Compose

Para construir las imágenes de los microservicios y levantar todo el ecosistema (RabbitMQ, PostgreSQL y los microservicios) en segundo plano:

```bash
docker compose up -d --build
```

### Reconstruir únicamente los microservicios tras cambios de código:

Si realizas cambios en el código de los microservicios y no deseas reiniciar las bases de datos ni RabbitMQ:

```bash
docker compose up -d --build cliente-service cuenta-service
```

*(O de forma individual: `docker compose up -d --build cliente-service` o `docker compose up -d --build cuenta-service`)*

### Otros comandos útiles:

- **Ver logs en tiempo real:**
  ```bash
  docker compose logs -f
  ```

- **Ver el estado de los contenedores:**
  ```bash
  docker compose ps
  ```

- **Detener los servicios:**
  ```bash
  docker compose down
  ```

- **Detener y reiniciar las bases de datos (borrando volúmenes):**
  ```bash
  docker compose down -v
  ```

---

## Puertos y Servicios Disponibles

| Servicio | Puerto Host | Descripción | Enlace |
| :--- | :--- | :--- | :--- |
| **RabbitMQ Management** | `15672` | Consola de RabbitMQ (user/pass: `guest`/`guest`) | [http://localhost:15672](http://localhost:15672) |
| **PostgreSQL** | `5433` | Base de datos (`tata` / `password`) | `localhost:5433` |
| **Customer Service** | `8080` | Swagger UI del servicio de clientes | [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) |
| **Account Service** | `8081` | Swagger UI del servicio de cuentas | [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html) |
