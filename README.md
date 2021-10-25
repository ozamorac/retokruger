# retokruger
Reto Kruger

Se ha realizado el desarrollo segun los criterios de aceptacion del reto

Modelo de Datos
---------------
![image](https://user-images.githubusercontent.com/30761344/138661330-f74037e8-2da7-4a40-ae92-eeaa5a4bdead.png)

Pasos para instalar y poner en marcha la aplicación

1. Ejecutar el script create-schema.sql que se encuentra en la ruta /src/main/resources/, el cual va a crear el schema, tablas y secuencuas que se usan. La base de datos es Postgres. Se recomienda crear el esquema en la base de datos por defecto del postgres.
2. Luego cambiar las credenciales de conexión que se encuentran en el archivo application.properties en la ruta /src/main/resources/, tags para conexion:
    spring.datasource.username=postgres
    spring.datasource.password=<Password>
3. Poner en marcha el aplicativo
4. Una vez en marcha el aplicativo, usar el postman y cargar el collection Reto Kruger.postman_collection.json que se encuentra en la ruta /src/main/resources/
  ![image](https://user-images.githubusercontent.com/30761344/138662458-3fb2af97-e1a5-4e2a-8ecf-7c3c0fb8b2a8.png)
5. Configurar el oauth para obtener el token
  ![image](https://user-images.githubusercontent.com/30761344/138662850-29ba0c67-f571-4ad9-9a72-5632020e40f8.png)
6. Los valores para las credenciales estan en la tabla Usuario
  
Criterios de Aceptación
-------------------------
1. Como Administrador requiere registrar, editar, listar y eliminar a los empleados.
Criterios de aceptación:
  a. Registrar la siguiente información del empleado.
    ○ Cédula.
    ○ Nombres.
    ○ Apellidos.
    ○ Correo electrónico.
  b. Los campos deben contener validaciones de acuerdo al tipo de dato:
    ○ Todos los campos son requeridos.
    ○ Cédula válida. (Incluir un valor numérico y único de 10 dígitos)
    ○ Correo electrónico válido.
    ○ Nombres y apellidos no deben contener números o caracteres especiales.
  c. Al dar de alta un empleado se debe generar un usuario y contraseña para el empleado.
2. Como Empleado requiero ingresar al sistema para visualizar y actualizar mi información.
    Criterios de aceptación:
    a. Completar la siguiente información:
    ● Fecha de nacimiento.
    ● Dirección de domicilio.
    ● Teléfono móvil.
    ● Estado de vacunación: Vacunado / No Vacunado.
    ● Si el empleado está en estado vacunado, se debe pedir la siguiente información
    requerida:
      ○ Tipo de vacuna: Sputnik, AstraZeneca, Pfizer y Jhonson&Jhonson
      ○ Fecha de vacunación.
      ○ Número de dosis.
3. Como Administrador se requiere filtrar el listado de los empleados por los siguientes criterios.
    Criterios de aceptación:
    a. Filtrar por estado de vacunación.
    b. Filtrar por tipo de vacuna.
    c. Filtrar por rango de fecha de vacunación.
