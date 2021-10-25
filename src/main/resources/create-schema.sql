CREATE SCHEMA inventario AUTHORIZATION postgres;

CREATE TABLE inventario.empleado(
  	id_empleado INTEGER NOT NULL,
  	cedula VARCHAR(10) NOT NULL,
	nombres VARCHAR(80) NOT NULL,
  	apellidos VARCHAR(80) NOT NULL,
	correo_electronico VARCHAR(80) NOT NULL,
  	fecha_nacimiento TIMESTAMP NULL,
	direccion VARCHAR(100) NULL,
	telefono_movil VARCHAR(15) NULL,
  	estado_vacunacion VARCHAR(1) NULL
);
ALTER TABLE IF EXISTS inventario.empleado ADD CONSTRAINT XPKEMPLEADO PRIMARY KEY(id_empleado);

CREATE SEQUENCE inventario."SEQ_EMPLEADO"
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 99999
   CACHE 1;
ALTER SEQUENCE inventario."SEQ_EMPLEADO" OWNER TO postgres;

ALTER TABLE IF EXISTS inventario.empleado ALTER COLUMN id_empleado SET DEFAULT nextval('inventario."SEQ_EMPLEADO"');

CREATE TABLE inventario.usuario (
	id_usuario INTEGER NOT NULL,
	id_empleado INTEGER NOT NULL,
	username varchar(15) NOT NULL,
	password varchar(255) NOT NULL,
	estado varchar(1) NOT NULL,
	password_nencrypt varchar(255) NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY (id_usuario),
	CONSTRAINT usuario_empleado_fk FOREIGN KEY (id_empleado) REFERENCES inventario.empleado(id_empleado)
);
CREATE INDEX usuario_id_empleado_idx ON inventario.usuario USING btree (id_empleado);
CREATE UNIQUE INDEX usuario_id_idx ON inventario.usuario USING btree (id_usuario);

CREATE SEQUENCE inventario."SEQ_USUARIO"
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 99999
   CACHE 1;
ALTER SEQUENCE inventario."SEQ_USUARIO" OWNER TO postgres;

ALTER TABLE IF EXISTS inventario.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('inventario."SEQ_USUARIO"');

CREATE TABLE inventario.vacuna (
	id_vacuna int4 NOT NULL,
	id_empleado int4 NOT NULL,
	tipo_vacuna varchar(100) NOT NULL,
	fecha_vacunacion date NULL,
	dosis int4 NULL,
	CONSTRAINT vacuna_pk PRIMARY KEY (id_vacuna),
	CONSTRAINT vacuna_fk FOREIGN KEY (id_empleado) REFERENCES inventario.empleado(id_empleado)
);

CREATE SEQUENCE inventario."SEQ_VACUNA"
   INCREMENT 1
   START 1
   MINVALUE 1
   MAXVALUE 99999
   CACHE 1;
ALTER SEQUENCE inventario."SEQ_VACUNA" OWNER TO postgres;

ALTER TABLE IF EXISTS inventario.vacuna ALTER COLUMN id_vacuna SET DEFAULT nextval('inventario."SEQ_VACUNA"');