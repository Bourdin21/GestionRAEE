package service;

import dao.TipoRAEEDAO;
import model.TipoRAEE;
import java.util.List;

public class TipoRAEEService {

    private TipoRAEEDAO tipoRAEEDAO = new TipoRAEEDAO();

    // Crear un nuevo tipo de RAEE
    public void crearTipoRAEE(TipoRAEE tipoRAEE) {
        tipoRAEEDAO.guardarTipoRAEE(tipoRAEE);
    }

    // Obtener un tipo de RAEE por ID
    public TipoRAEE obtenerTipoRAEEPorId(int id) {
        return tipoRAEEDAO.obtenerTipoRAEEPorId(id);
    }

    // Obtener todos los tipos de RAEE
    public List<TipoRAEE> obtenerTodosLosTiposRAEE() {
        return tipoRAEEDAO.obtenerTiposRAEE();
    }

    // Eliminar un tipo de RAEE
    public void eliminarTipoRAEE(int id) {
        tipoRAEEDAO.eliminarTipoRAEE(id);
    }
}
