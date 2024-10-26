package service;

import dao.CantidadTipoDAO;
import model.CantidadTipo;
import java.util.List;

public class CantidadTipoService {

    private CantidadTipoDAO cantidadTipoDAO = new CantidadTipoDAO();

    // Crear y guardar una cantidad de TipoRAEE para una solicitud
    public void agregarTipoRAEEASolicitud(CantidadTipo cantidadTipo) {
        cantidadTipoDAO.guardarCantidadTipo(cantidadTipo);
    }

    // Obtener todas las cantidades de TipoRAEE asociadas a una solicitud
    public List<CantidadTipo> obtenerCantidadesPorSolicitud(int idSolicitud) {
        return cantidadTipoDAO.obtenerCantidadesPorSolicitud(idSolicitud);
    }

    // Eliminar una cantidad de TipoRAEE de una solicitud
    public void eliminarCantidadTipo(int idCantidadTipo) {
        cantidadTipoDAO.eliminarCantidadTipo(idCantidadTipo);
    }
}
