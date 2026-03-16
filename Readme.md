# README del proyecto

## 📌 Descripción del proyecto

Este proyecto consiste en una **API para la gestión de productos** mediante distintos endpoints.
Incluye **autenticación mediante JWT** y un ejemplo de **frontend que consume la API usando Fetch API**.

---

# ⚙️ Instalación y ejecución

## 1. Clonar el repositorio

```bash
git clone <url-del-repositorio>
cd <nombre-del-proyecto>
```

## 2. Instalar dependencias

```bash
npm install
```

## 3. Ejecutar el proyecto

```bash
npm start
```

## 4. Acceder a la documentación de la API (Swagger)

```text
http://localhost:<puerto>/api-docs
```

---

# 🔗 Ejemplos de endpoints

## Obtener productos

### Endpoint

```http
GET /productos
```

### Ejemplo de respuesta

```json
[
  {
    "id": 1,
    "nombre": "Producto 1",
    "precio": 100
  },
  {
    "id": 2,
    "nombre": "Producto 2",
    "precio": 200
  }
]
```

---

## Autenticación

### Endpoint

```http
POST /auth/login
```

### Body

```json
{
  "email": "usuario@email.com",
  "password": "123456"
}
```

### Respuesta

```json
{
  "token": "jwt_token_aqui"
}
```

---

# 📷 Capturas de Swagger y pruebas

En esta sección se deben incluir capturas de pantalla de:

* Documentación generada con **Swagger**
* Pruebas realizadas a los endpoints
* Ejecución correcta de los métodos de la API

### Ejemplo de estructura

```text
/docs
 ├── swagger.png
 └── pruebas-endpoints.png
```

---

# 💻 Carpeta `frontend`

El proyecto incluye una carpeta `frontend/` con un **ejemplo simple de consumo de la API**.

## Estructura

```text
frontend/
 ├── index.html
 ├── style.css
 └── app.js
```

## Funcionalidad

* Contiene un botón que consulta el endpoint:

```http
GET /productos
```

* La lista de productos se muestra en pantalla utilizando **Fetch API**.

## Archivos incluidos

```text
index.html
style.css
app.js
```

---

# 📄 Documento explicativo (PDF)

El proyecto incluye un documento explicativo en **PDF** que contiene:

* **Diagrama de clases (simplificado)**
* **Descripción de arquitectura**
* **Ejemplo de token JWT**
* **Uso del token en headers**

## Ejemplo de ubicación

```text
/docs
 └── documento-explicativo.pdf
```
