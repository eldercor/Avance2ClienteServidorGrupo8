package grupo_8_sc303_mv_proyectono1;

public class Usuario {
    private int id; // Identificador único del usuario
    private String nombre; // Nombre del usuario
    private String contraseña; // Contraseña del usuario
    private String rol; // Rol del usuario (ej. Administrador, Mesero)
    private String sede; // Sede donde trabaja el usuario

    // Constructor para inicializar el usuario con los parámetros dados
    public Usuario(int id, String nombre, String contraseña, String rol, String sede) {
        this.id = id; // Asignar ID del usuario
        this.nombre = nombre; // Asignar nombre del usuario
        this.contraseña = contraseña; // Asignar contraseña del usuario
        this.rol = rol; // Asignar rol del usuario
        this.sede = sede; // Inicializar la sede
    }

    // Método para verificar las credenciales del usuario
    public boolean verificarCredenciales(String nombreUsuario, String contraseña) {
        return this.nombre.equals(nombreUsuario) && this.contraseña.equals(contraseña); // Comparar nombre y contraseña
    }

    // Getter para obtener el ID del usuario
    public int getId() {
        return id; // Retorna el ID del usuario
    }

    // Getter para obtener el nombre del usuario
    public String getNombre() {
        return nombre; // Retorna el nombre del usuario
    }

    // Getter para obtener la contraseña del usuario
    public String getContraseña() {
        return contraseña; // Retorna la contraseña del usuario
    }

    // Getter para obtener el rol del usuario
    public String getRol() {
        return rol; // Retorna el rol del usuario
    }

    // Getter para obtener la sede del usuario
    public String getSede() {
        return sede; // Retorna la sede del usuario
    }

    // Setter para actualizar el nombre del usuario
    public void setNombre(String nombre) {
        this.nombre = nombre; // Actualiza el nombre del usuario
    }

    // Setter para actualizar la contraseña del usuario
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña; // Actualiza la contraseña del usuario
    }

    // Setter para actualizar el rol del usuario
    public void setRol(String rol) {
        this.rol = rol; // Actualiza el rol del usuario
    }

    // Setter para actualizar la sede del usuario
    public void setSede(String sede) {
        this.sede = sede; // Actualiza la sede del usuario
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                ", sede='" + sede + '\'' +
                '}'; // Retorna una representación en cadena del usuario
    }
}