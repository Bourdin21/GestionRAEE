package test;

import model.Usuario;
import model.CentroRecoleccion;
import model.Solicitud;
import model.TipoRAEE;
import service.UsuarioService;
import service.CentroRecoleccionService;
import service.SolicitudService;
import service.TipoRAEEService;

import java.util.List;

public class TestServicios {

    public static void main(String[] args) {
        UsuarioService usuarioService = new UsuarioService();
        CentroRecoleccionService centroRecoleccionService = new CentroRecoleccionService();
        SolicitudService solicitudService = new SolicitudService();
        TipoRAEEService tipoRAEEService = new TipoRAEEService();

        // Prueba de UsuarioService
        Usuario nuevoUsuario = new Usuario("Ana Lopez", "Av. Libertador 123", "ana@mail.com", "0987654321");
        usuarioService.crearUsuario(nuevoUsuario);
        System.out.println("Usuario creado: " + nuevoUsuario);

        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        System.out.println("Lista de usuarios:");
        usuarios.forEach(usuario -> System.out.println(usuario.getNombre()));

        Usuario usuarioRecuperado = usuarioService.obtenerUsuarioPorId(nuevoUsuario.getIdUsuario());
        System.out.println("Usuario obtenido por ID: " + usuarioRecuperado.getNombre());

        usuarioRecuperado.setDireccion("Nueva Dirección 456");
        usuarioService.actualizarUsuario(usuarioRecuperado);
        System.out.println("Usuario actualizado: " + usuarioRecuperado.getDireccion());

        usuarioService.eliminarUsuario(usuarioRecuperado.getIdUsuario());
        System.out.println("Usuario eliminado.");

        // Prueba de CentroRecoleccionService
        CentroRecoleccion nuevoCentro = new CentroRecoleccion("Centro Norte", "Calle Principal 789", 500);
        centroRecoleccionService.crearCentroRecoleccion(nuevoCentro);
        System.out.println("Centro de recolección creado: " + nuevoCentro.getNombre());

        List<CentroRecoleccion> centros = centroRecoleccionService.obtenerTodosLosCentros();
        System.out.println("Lista de centros de recolección:");
        centros.forEach(centro -> System.out.println(centro.getNombre()));

        CentroRecoleccion centroRecuperado = centroRecoleccionService.obtenerCentroPorId(nuevoCentro.getIdCentro());
        System.out.println("Centro de recolección obtenido por ID: " + centroRecuperado.getNombre());

        centroRecuperado.setCapacidad(750);
        centroRecoleccionService.actualizarCentro(centroRecuperado);
        System.out.println("Centro actualizado: Capacidad nueva = " + centroRecuperado.getCapacidad());

        centroRecoleccionService.eliminarCentro(centroRecuperado.getIdCentro());
        System.out.println("Centro de recolección eliminado.");

        // Prueba de TipoRAEEService
        TipoRAEE nuevoTipo = new TipoRAEE("Electrodomésticos");
        tipoRAEEService.crearTipoRAEE(nuevoTipo);
        System.out.println("Tipo de RAEE creado: " + nuevoTipo.getDescripcion());

        List<TipoRAEE> tiposRAEE = tipoRAEEService.obtenerTodosLosTiposRAEE();
        System.out.println("Lista de tipos de RAEE:");
        tiposRAEE.forEach(tipo -> System.out.println(tipo.getDescripcion()));

        TipoRAEE tipoRecuperado = tipoRAEEService.obtenerTipoRAEEPorId(nuevoTipo.getIdTipo());
        System.out.println("Tipo de RAEE obtenido por ID: " + tipoRecuperado.getDescripcion());

        tipoRAEEService.eliminarTipoRAEE(tipoRecuperado.getIdTipo());
        System.out.println("Tipo de RAEE eliminado.");

        // Crear un nuevo usuario y guardarlo
        nuevoUsuario = new Usuario("Ana Lopez", "Av. Libertador 123", "ana@mail.com", "0987654321");
        usuarioService.crearUsuario(nuevoUsuario);

        // Recuperar el usuario recién creado para obtener su ID generado
        usuarioRecuperado = usuarioService.obtenerUsuarioPorId(nuevoUsuario.getIdUsuario());

        // Crear un nuevo centro de recolección y guardarlo
        nuevoCentro = new CentroRecoleccion("Centro Norte", "Calle Principal 789", 500);
        centroRecoleccionService.crearCentroRecoleccion(nuevoCentro);

        // Recuperar el centro recién creado para obtener su ID generado
        centroRecuperado = centroRecoleccionService.obtenerCentroPorId(nuevoCentro.getIdCentro());

        // Verifica que el usuario y el centro existan antes de crear la solicitud
        if (usuarioRecuperado != null && centroRecuperado != null) {
            // Crear una nueva solicitud usando los objetos recuperados de usuario y centro
            Solicitud nuevaSolicitud = new Solicitud(usuarioRecuperado, centroRecuperado, "Ingresada");
            solicitudService.crearSolicitud(nuevaSolicitud);
            System.out.println("Solicitud creada con estado: " + nuevaSolicitud.getEstado());
        } else {
            System.out.println("Error: No se pudo encontrar el usuario o centro de recolección necesario para crear la solicitud.");
        }

        // Prueba de SolicitudService
        Solicitud nuevaSolicitud = new Solicitud(usuarioRecuperado, centroRecuperado, "Ingresada");
        solicitudService.crearSolicitud(nuevaSolicitud);
        System.out.println("Solicitud creada con estado: " + nuevaSolicitud.getEstado());

        List<Solicitud> solicitudes = solicitudService.obtenerTodasLasSolicitudes();
        System.out.println("Lista de solicitudes:");
        solicitudes.forEach(solicitud -> System.out.println("Solicitud ID: " + solicitud.getIdSolicitud()));

        Solicitud solicitudRecuperada = solicitudService.obtenerSolicitudPorId(nuevaSolicitud.getIdSolicitud());
        System.out.println("Solicitud obtenida por ID: Estado = " + solicitudRecuperada.getEstado());

        solicitudService.actualizarEstadoSolicitud(solicitudRecuperada.getIdSolicitud(), "En Proceso");
        System.out.println("Solicitud actualizada a estado: " + solicitudRecuperada.getEstado());

        solicitudService.eliminarSolicitud(solicitudRecuperada.getIdSolicitud());
        System.out.println("Solicitud eliminada.");
    }
}
