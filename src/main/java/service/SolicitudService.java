package service;

import dao.SolicitudDAO;
import model.Solicitud;
import java.util.List;

public class SolicitudService {

    private SolicitudDAO solicitudDAO = new SolicitudDAO();

    // Crear una nueva solicitud de reciclaje
    public void crearSolicitud(Solicitud solicitud) {
        solicitudDAO.guardarSolicitud(solicitud);
    }

    // Obtener una solicitud por ID
    public Solicitud obtenerSolicitudPorId(int id) {
        return solicitudDAO.obtenerSolicitudPorId(id);
    }

    // Método para obtener solicitudes por ID de usuario
    public List<Solicitud> obtenerSolicitudesPorUsuario(int idUsuario) {
        return solicitudDAO.obtenerSolicitudesPorUsuario(idUsuario);
    }

    // Obtener todas las solicitudes
    public List<Solicitud> obtenerTodasLasSolicitudes() {
        return solicitudDAO.obtenerSolicitudes();
    }

    // Actualizar el estado de una solicitud
    public void actualizarEstadoSolicitud(int idSolicitud, String nuevoEstado) {
        Solicitud solicitud = solicitudDAO.obtenerSolicitudPorId(idSolicitud);
        if (solicitud != null) {
            solicitud.setEstado(nuevoEstado);
            solicitudDAO.actualizarSolicitud(solicitud);
        }
    }

    // Eliminar una solicitud
    public void eliminarSolicitud(int id) {
        solicitudDAO.eliminarSolicitud(id);
    }
}
