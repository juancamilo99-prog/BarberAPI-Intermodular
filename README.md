<h1>:barber: BarberAPI</h1>

<p>BarberAPI es una aplicación web diseñada para gestionar una barbería de forma sencilla y eficiente.
El sistema permite administrar clientes, citas y servicios de barbería.

Este proyecto ha sido desarrollado como práctica de desarrollo web para el proyecto Intermodular the power, aplicando buenas prácticas de programación y organización del código, como: arquitectura por capas (Controller, DAO), conexión a base de datos y despliegue en la nube. 🚀</p>


<h2> 🌐 Demo </h2>

    👉 Aplicación desplegada: https://barberapi-intermodular.onrender.com

    ⚠️ Nota: el servicio puede tardar unos segundos en cargar debido al plan gratuito de Render.

<h2> Tecnologías utilizadas 🚀</h2>

-  Backend:
    -  Java 21
    - Spring Boot
    - JDCB (conexión MySQL)
-  Frontend:
    - HTML
    - CSS
    - JavaScript
-  Base de datos:
    - MySQL (Railway)
-  Despliegue
    - Render (backend)
    - Railway (base de datos)

<h2>:file_folder: Estructura de Carpetas</h2>
    
    BarberAPI/
    │
    ├── web/                          # Interfaz web (frontend)
    │   ├── index.html                # Página principal
    │   │
    │   ├── styles/                  # Archivos CSS
    │   │   └── styles.css
    │   │
    │   └── assets/                  # Recursos estáticos
    │   │   └── images/              # Imágenes (hero, iconos, etc.)
    │   │
    │   └── js/ 
    │       └── app.js/              #JavaScript de la web.  
    │
    ├── barberapi/                  # Backend Spring Boot
    │   ├── src/
    │   │   ├── main/
    │   │   │   ├── java/           # Controllers, DAO, modelos
    │   │   │   └── resources/
    │   │   │       ├── templates/  # Vistas HTML (Thymeleaf)
    │   │   │       └── static/     # CSS, JS
    │   │   ├── pom.xml
    │   │   └── Dockerfile
    ├── docs/                        # Documentación del proyecto
    │   ├── bd/                      # Documentación de base de datos
    │   │   └── diagramas/           # Diagramas de la BD
    │   │
    │   └── documentacion/           # Documentación general
    │
    ├── sql/                         # Scripts SQL (tablas, inserts, queries)
    │
    └── README.md                   # Información general del proyecto
-  web/ → Contiene los archivos de la interfaz web del proyecto (HTML, CSS y JavaScript).
- barberapi/ → Incluye el código fuente principal de la aplicación y la lógica del sistema.
- docs/ → Carpeta destinada a documentación del proyecto, diagramas o recursos adicionales.
- sql/ → Carpeta destinada a los scripts sql de la bases de datos
- README.md → Documento principal con la información del proyecto, instalación y uso.

<h2> 🧠 Funcionalidades </h2>
<p> 👤 Gestión de clientes
  
- Creacion automatica de clientes al hacer una reserva
- Validación de datos (nombre, email, telefono)
- Evita duplicados por correo o telefono
</p>
<p> 📅 Gestión de reservas
  
- Crear reservas con fecha y hora
- Bloqueo de:
    - fechas pasadas
    - horas ocupadas
- Validación:
    - un cliente no puede reservar mas de una cita al dia
</p>
<p> 💇 Servicios
  
- Listado dinámico desde base de datos
- Informacion: nombre, precio y duración
</p>

<h2> 🗄️ BASE DE DATOS </h2>
<p> Tablas principales 

    - clientes
    - reservas
    - servicios
</p>
<p> Relaciones

    - Un cliente puede tener varias reservas
    - Una reserva pertenece a un servicio
</p>
<p> Restricciones

    - Email y telefono unicos
    - Claves foráneas entre tablas
</p>

<h2> ⚙️ Configuracion del entorno </h2>

<p> El proyecto usa variables de entorno para la conexión a la base de datos: </p>

      </> env
        DB_URL=jdbc:mysql://HOST:PORT/railway?allowPublicKeyRetrieval=true&useSSL=false
        DB_USER=root
        DB_PASSWORD=*******
        PORT=0000


<h2> 🐳 Despliegue </h2>

<p> <Strong>Backend</Strong>
  
- Desplegado en render usando Docker.
</p>
<p> <Strong>Base de datos</Strong>
  
- Alojada en Railway.
</p>

<h2> 📌 Aprendizajes </h2>
<p> 

- Conexión JDBC en producción
- Gestión de errores SQL
- Uso de variables de entorno
- Despliegue con Docker
- Integración backend + frontend
- Resolucion de conflictos y debugging real
</p>

<h2> 💡 Posibles mejoras </h2>
<p> 

- Panel de administración
- Login de usuarios
- Cancelación de reservas
- Cancelación de reservas
- API REST completa
- Notificaciones por email
</p>

<h2> 👨‍💻 AUTORES </h2>
<p>Proyecto desarrollado por:  

- Juan Camilo Montero Mosquera
- Jheremi Alexander Loachamin Lema 

como parte del módulo Intermodular</p>