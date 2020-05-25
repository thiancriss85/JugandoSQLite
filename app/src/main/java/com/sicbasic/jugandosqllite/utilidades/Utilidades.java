package com.sicbasic.jugandosqllite.utilidades;

public class Utilidades {

    //Constantes de la tabla Usuarios
    public static final  String TABLA_USUARIO="usuario";
    public static final  String CAMPO_ID="id";
    public static final  String CAMPO_NOMBRE="nombre";
    public static final  String CAMPO_TELEFONO="telefono";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_TELEFONO+" TEXT)";

    //Constantes de la tabla Mascotas

    public static final  String TABLA_MASCOTA="mascota";
    public static final  String CAMPO_ID_MASCOTA="id_mascota";
    public static final  String CAMPO_NOMBRE_MASCOTA="nombre_mascota";
    public static final  String CAMPO_RAZA_MASCOTA="raza_mascota";
    public static final  String CAMPO_ID_DUENO="id_dueno";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE "+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA_MASCOTA+" TEXT, "+CAMPO_ID_DUENO+" INTEGER)";
}
