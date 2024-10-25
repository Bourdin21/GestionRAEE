package service;

import dao.CentroRecoleccionDAO;
import model.CentroRecoleccion;
import java.util.List;

public class CentroRecoleccionService {

    private CentroRecoleccionDAO centroRecoleccionDAO = new CentroRecoleccionDAO();

    // Crear un nuevo centro de recolección
    public void crearCentroRecoleccion(CentroRecoleccion centro) {
        centroRecoleccionDAO.guardarCentro(centro);
    }

    // Obtener un centro de recolección por ID
    public CentroRecoleccion obtenerCentroPorId(int id) {
        return centroRecoleccionDAO.obtenerCentroPorId(id);
    }

    // Obtener todos los centros de recolección
    public List<CentroRecoleccion> obtenerTodosLosCentros() {
        return centroRecoleccionDAO.obtenerCentros();
    }

    // Actualizar un centro de recolección
    public void actualizarCentro(CentroRecoleccion centro) {
        centroRecoleccionDAO.actualizarCentro(centro);
    }

    // Eliminar un centro de recolección
    public void eliminarCentro(int id) {
        centroRecoleccionDAO.eliminarCentro(id);
    }
}
