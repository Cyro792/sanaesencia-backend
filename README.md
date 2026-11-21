# Sanaesencia Backend — Microservicios & API Gateway

Backend para el Centro Médico Sanaesencia, desarrollado en Spring Boot (Java 17 / JPA) y Oracle Autonomous Database con conexión mTLS Wallet, orquestado mediante Docker Compose y Nginx API Gateway.

---

## 🏛️ Arquitectura de la Solución

```
                                      +------------------+
                                      |   Cliente App    |
                                      +--------+---------+
                                               |
                                        HTTP (Puerto 80)
                                               v
                                    +----------+----------+
                                    | Nginx API Gateway  |
                                    +----+-----+-----+----+
                                         |     |     |
                 +-----------------------+     |     +-----------------------+
                 |                             |                             |
                 v                             v                             v
       +---------+---------+         +---------+---------+         +---------+---------+
       |   ms-pacientes    |         |    ms-catalogo    |         |    ms-agenda      |
       |  (Puerto 8081)    |         |  (Puerto 8082)    |         |  (Puerto 8083)    |
       +---------+---------+         +---------+---------+         +---------+---------+
                 |                             |                             |
                 +-----------------------------+-----------------------------+
                                               |
                                     Oracle Cloud Autonomous DB
                                       (vetsm_high via mTLS)
```

---

## 📌 Tabla de Endpoints REST

### 1. Microservicio Pacientes (`ms-pacientes` — Puerto 8081)
| Método | Endpoint | Descripción | Respuestas |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/pacientes` | Lista todos los pacientes registrados | `200 OK` |
| **GET** | `/api/pacientes/{id}` | Obtiene un paciente por ID | `200 OK`, `404 Not Found` |
| **GET** | `/api/pacientes/rut/{run}` | Busca paciente por RUN (sin puntos ni DV) | `200 OK`, `404 Not Found` |
| **GET** | `/api/pacientes/email/{email}` | Busca paciente por dirección de email | `200 OK`, `404 Not Found` |
| **POST** | `/api/pacientes` | Registra un nuevo paciente (valida RUN/Email únicos) | `201 Created`, `409 Conflict` |
| **PUT** | `/api/pacientes/{id}` | Actualiza información del paciente | `200 OK`, `404 Not Found` |
| **DELETE** | `/api/pacientes/{id}` | Elimina un paciente por ID | `204 No Content`, `404 Not Found` |

### 2. Microservicio Catálogo (`ms-catalogo` — Puerto 8082)
| Método | Endpoint | Descripción | Respuestas |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/productos` | Lista todos los productos naturales | `200 OK` |
| **GET** | `/api/productos/{id}` | Obtiene producto por ID | `200 OK`, `404 Not Found` |
| **GET** | `/api/productos/categoria/{cat}` | Filtra productos por categoría | `200 OK` |
| **POST** | `/api/productos` | Registra un nuevo producto | `201 Created` |
| **PUT** | `/api/productos/{id}` | Actualiza datos de un producto | `200 OK`, `404 Not Found` |
| **PUT** | `/api/productos/{id}/stock?cantidad={n}` | Descuenta `n` unidades de stock tras compra | `200 OK`, `400 Bad Request`, `404 Not Found` |
| **DELETE** | `/api/productos/{id}` | Elimina un producto | `204 No Content`, `404 Not Found` |
| **GET** | `/api/especialistas` | Lista los especialistas del centro médico | `200 OK` |
| **GET** | `/api/especialistas/{id}` | Obtiene especialista por ID | `200 OK`, `404 Not Found` |
| **POST** | `/api/especialistas` | Registra un nuevo especialista | `201 Created` |
| **PUT** | `/api/especialistas/{id}` | Actualiza un especialista | `200 OK`, `404 Not Found` |
| **DELETE** | `/api/especialistas/{id}` | Elimina un especialista | `204 No Content`, `404 Not Found` |

### 3. Microservicio Agenda (`ms-agenda` — Puerto 8083)
| Método | Endpoint | Descripción | Respuestas |
| :--- | :--- | :--- | :--- |
| **GET** | `/api/citas` | Lista todas las citas médicas agendadas | `200 OK` |
| **GET** | `/api/citas/{id}` | Obtiene cita por ID | `200 OK`, `404 Not Found` |
| **GET** | `/api/citas/paciente/{pacienteId}` | Obtiene citas de un paciente específico | `200 OK` |
| **GET** | `/api/citas/disponibilidad?especialistaId={id}&fecha={fecha}` | Consulta citas ocupadas para un especialista en una fecha | `200 OK` |
| **POST** | `/api/citas` | Agenda una nueva cita (valida disponibilidad horaria) | `201 Created`, `409 Conflict` |
| **PUT** | `/api/citas/{id}/estado?estado={ESTADO}` | Actualiza el estado (`PENDIENTE_PAGO`, `CONFIRMADA`, `CANCELADA`, `COMPLETADA`) | `200 OK`, `404 Not Found` |
| **DELETE** | `/api/citas/{id}` | Elimina una cita agendada | `204 No Content`, `404 Not Found` |

---

## 🚀 Despliegue en AWS EC2 (Docker Compose)

### Prerrequisitos en EC2:
1. Clonar el repositorio `sanaesencia-backend`.
2. Asegurarse de que el directorio `Wallet/` contenga las credenciales descomprimidas de Oracle Cloud.

### Comandos de Despliegue:

```bash
# 1. Compilar e iniciar todos los contenedores en segundo plano
docker compose up -d --build

# 2. Monitorear los logs de todos los servicios en tiempo real
docker compose logs -f

# 3. Verificar el estado de los contenedores activos
docker compose ps

# 4. Detener la infraestructura cuando sea necesario
docker compose down
```
