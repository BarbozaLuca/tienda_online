# 🧩 Proyecto Final - Microservicios con Spring Cloud

**Descripción breve:**  
Proyecto final con arquitectura de microservicios usando Spring Cloud. Incluye Eureka, Config Server, API Gateway, Circuit Breaker, Load Balancing y comunicación entre servicios con Feign. Persistencia con JPA y MySQL. Dockerizado con Docker Compose para facilitar su ejecución.

---

## 📌 Descripción general

Este proyecto implementa una arquitectura de microservicios desacoplada utilizando **Spring Cloud**. Cada servicio es independiente, se registra en un **Service Discovery (Eureka)**, se comunica a través de **Feign Clients**, y expone sus rutas mediante un **API Gateway**.  
La configuración se centraliza con un **Config Server** y se aplican patrones como **Circuit Breaker** y **Load Balancing** para mejorar la resiliencia y disponibilidad del sistema.

---
## 📖 Escenario del problema

Este proyecto simula el backend de una tienda online de electrodomésticos mediante una arquitectura de microservicios. El sistema fue desarrollado a partir de los requerimientos funcionales especificados por un analista, en conjunto con el encargado de la tienda.
El sistema está compuesto por tres microservicios que se comunican entre sí para ofrecer una experiencia de compra fluida a los usuarios:

- **Microservicio de Productos:** Gestiona la información de los electrodomésticos disponibles. Permite listar productos y consultar detalles como código, nombre, marca y precio.

- **Microservicio de Carrito de Compras:** Administra el carrito de compras de cada usuario. Permite agregar y eliminar productos, y mantiene el total acumulado del carrito.

- **Microservicio de Ventas:** Registra cada venta mediante un identificador y una fecha. Cada venta está asociada a un carrito, por lo que este servicio se comunica con el de carrito y, a través de él, con el de productos, para conocer el monto total y la lista de productos vendidos.

Cada servicio está desacoplado y se comunica a través de un API Gateway, con balanceo de carga, descubrimiento de servicios, configuración centralizada y circuit breaker para mayor resiliencia.

---

## 🧱 Arquitectura y patrones implementados

- ✅ **Service Discovery** – Eureka Server
- ✅ **API Gateway** – Spring Cloud Gateway
- ✅ **Configuración Centralizada** – Spring Cloud Config Server
- ✅ **Circuit Breaker** – Resilience4j
- ✅ **Balanceo de carga** – Feign + Eureka
- ✅ **Comunicación entre servicios** – OpenFeign
- ✅ **Persistencia** – Spring Data JPA + MySQL
- ✅ **Contenerización** – Docker y Docker Compose

---

## 🛠️ Tecnologías utilizadas

- Java 17  
- Spring Boot  
- Spring Cloud (Eureka, Gateway, Config, Feign)  
- Resilience4j  
- Spring Data JPA  
- MySQL  
- Docker / Docker Compose  
- Postman (para pruebas de endpoints)

---

## 🚀 Ejecución del proyecto

### Prerrequisitos

- Docker y Docker Compose instalados
- JDK 17

---

### Pasos

1. Clonar el repositorio:

   git clone https://github.com/BarbozaLuca/tienda_online.git
   
2. Construir los servicios:
   
    docker-compose build

3. Levantar los contenedores:

   docker-compose up
   
---

## 📬 Contacto
- Luca Nicolas Barboza - barbozaluca04@gmail.com
- Enlace del proyecto: https://github.com/BarbozaLuca/tienda_online.git
