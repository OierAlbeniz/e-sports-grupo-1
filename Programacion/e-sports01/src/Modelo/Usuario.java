package Modelo;
/**
 * La clase Usuario representa a un usuario del sistema.
 */
public class Usuario {
    private Integer idUsuario;
    private String nombre;
    private String contrasena;
    private String tipo;
    /**
     * Constructor de la clase Usuario.
     *
     * @param idUsuario   El identificador del usuario.
     * @param nombre      El nombre del usuario.
     * @param contrasena  La contraseña del usuario.
     * @param tipo        El tipo de usuario (puede ser "usuario" o "administrador").
     */
    public Usuario(Integer idUsuario, String nombre, String contrasena, String tipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    public Usuario() {

    }


    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
