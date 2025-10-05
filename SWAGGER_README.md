# Documentación Swagger - MicroServicio4

## 📚 Descripción
Este microservicio ahora incluye documentación completa de API usando Swagger/OpenAPI 3.0.

## 🚀 Cómo acceder a la documentación

### 1. Iniciar la aplicación
```bash
./mvnw spring-boot:run
```

### 2. Acceder a Swagger UI
Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación interactiva de Swagger en:

**Swagger UI:** http://localhost:8080/swagger-ui.html

**API Docs (JSON):** http://localhost:8080/api-docs

## 📋 Endpoints documentados

### Dashboard
- **GET** `/getDashboardInfo` - Obtener información del dashboard
- **GET** `/health` - Health check del microservicio

### Administrador (Requiere autenticación JWT)
- **POST** `/admin/sendemail/{id}` - Enviar emails globales (solo admins)

## 🔐 Autenticación

Los endpoints de administrador requieren autenticación JWT. Para usar estos endpoints:

1. Obtén un token JWT válido de tu sistema de autenticación
2. En Swagger UI, haz clic en el botón "Authorize" (🔒)
3. Introduce el token en el formato: `Bearer tu_token_aqui`
4. Haz clic en "Authorize"

## 📝 Ejemplo de uso

### Enviar emails globales
```json
POST /admin/sendemail/123
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

[
  {
    "adminId": 123,
    "usedHashTag": "#viral #trending",
    "postId": "7123456789012345678",
    "datePosted": "2024-01-15",
    "usernameTiktokAccount": "@username",
    "postURL": "https://www.tiktok.com/@username/video/7123456789012345678",
    "views": 15000,
    "likes": 850,
    "engagement": 0.056
  }
]
```

## 🛠️ Configuración adicional

La configuración de Swagger se encuentra en:
- **Configuración:** `src/main/java/com/example/demo/Config/OpenApiConfig.java`
- **Propiedades:** `src/main/resources/application.properties`

### 🌍 Variables de entorno para deployment

Puedes personalizar la documentación usando estas variables de entorno:

```bash
# Información de la aplicación
APP_NAME="Mi Microservicio API"
APP_DESCRIPTION="Descripción personalizada de mi API"
APP_VERSION="2.0.0"
APP_CONTACT_NAME="Mi Equipo"
APP_CONTACT_EMAIL="mi-equipo@empresa.com"
APP_LICENSE_NAME="Licencia Corporativa"
APP_LICENSE_URL="https://mi-empresa.com/licencia"

# Puerto del servidor
SERVER_PORT=8080

# URL base para producción (importante para deployment)
API_BASE_URL="https://mi-api.empresa.com"

# Perfil de Spring (local, development, production)
SPRING_PROFILES_ACTIVE=production
```

### 🚀 Ejemplo de deployment con Docker

```bash
# Ejemplo de docker-compose.yml
version: '3.8'
services:
  microservicio:
    image: tu-microservicio:latest
    environment:
      - API_BASE_URL=https://api.tu-dominio.com
      - SPRING_PROFILES_ACTIVE=production
      - APP_NAME="API de Producción"
      - APP_CONTACT_EMAIL="produccion@tu-empresa.com"
    ports:
      - "8080:8080"
```

## 📦 Dependencias añadidas
- `springdoc-openapi-starter-webmvc-ui:2.2.0`

## 🎯 Características incluidas
- ✅ Documentación completa de todos los endpoints
- ✅ Esquemas de datos con ejemplos
- ✅ Códigos de respuesta HTTP documentados
- ✅ Autenticación JWT configurada
- ✅ Validaciones de entrada documentadas
- ✅ Interfaz interactiva para probar endpoints
- ✅ Organización por tags (Dashboard, Administrador)
