package client;

import model.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private static final UsuarioService usuarioService = new UsuarioService();
    private static final CentroRecoleccionService centroRecoleccionService = new CentroRecoleccionService();
    private static final SolicitudService solicitudService = new SolicitudService();
    private static final TipoRAEEService tipoRAEEService = new TipoRAEEService();
    private static final CantidadTipoService cantidadTipoService = new CantidadTipoService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenido al Sistema de Gestión de RAEE.");

        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine(); // Lee la línea completa

        Usuario usuarioActual = usuarioService.obtenerUsuarioPorNombre(nombreUsuario); // Método que busca usuario por nombre

        if (usuarioActual == null) {
            System.out.println("Usuario no encontrado. Verifique el nombre.");
            return;
        }

        System.out.println("Bienvenido, " + usuarioActual.getNombre() + " (Rol: " + usuarioActual.getRol() + ")");

        boolean ejecutar = true;

        while (ejecutar) {
            System.out.println("\n----- Menú Principal -----");

            // Mostrar opciones según el rol del usuario
            if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
                System.out.println("1. Gestionar Usuarios");
                System.out.println("2. Gestionar Centros de Recolección");
                System.out.println("3. Gestionar Solicitudes");
            } else if (usuarioActual.getRol() == Usuario.Rol.USER) {
                System.out.println("1. Crear Solicitud");
                System.out.println("2. Consultar Estado de Solicitud");
            }
            System.out.println("4. Salir");

            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
                        gestionarUsuarios(usuarioActual);
                    } else if (usuarioActual.getRol() == Usuario.Rol.USER) {
                        crearSolicitudUsuario(usuarioActual); // Método para que el usuario cree su propia solicitud
                    }
                    break;
                case 2:
                    if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
                        gestionarCentros(usuarioActual);
                    } else if (usuarioActual.getRol() == Usuario.Rol.USER) {
                        consultarEstadoSolicitudUsuario(usuarioActual); // Método para que el usuario consulte su solicitud
                    }
                    break;
                case 3:
                    if (usuarioActual.getRol() == Usuario.Rol.ADMIN) {
                        gestionarSolicitudes(usuarioActual);
                    } else {
                        System.out.println("Opción no válida.");
                    }
                    break;
                case 4:
                    ejecutar = false;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    // Caso de uso: Gestionar Usuarios
    private static void gestionarUsuarios(Usuario usuarioActual) {
        System.out.println("\n-------------------- Gestión de Usuarios --------------------");

        // Mostrar lista de usuarios existentes
        System.out.println("\n--- Lista de Usuarios Existentes ---");
        consultarUsuarios(); // Llama al metodo que lista todos los usuarios
        System.out.println("------------------------------------");


        System.out.println("1. Crear Usuario");
        System.out.println("2. Consultar Usuarios");
        System.out.println("3. Actualizar Usuario");
        System.out.println("4. Eliminar Usuario");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                crearUsuario();
                break;
            case 2:
                consultarUsuarios();
                break;
            case 3:
                actualizarUsuario();
                break;
            case 4:
                eliminarUsuario();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void crearUsuario()
    {
        // Mostrar lista de usuarios existentes
        System.out.println("\n--- Lista de Usuarios Existentes ---");
        consultarUsuarios(); // Llama al metodo que lista todos los usuarios
        System.out.println("------------------------------------");
        System.out.println("\n--- Crear Nuevo Usuario ---");
        System.out.print("Nombre: ");
        String nombre = scanner.next();
        System.out.print("Dirección: ");
        String direccion = scanner.next();
        System.out.print("Email: ");
        String email = scanner.next();
        System.out.print("Teléfono: ");
        String telefono = scanner.next();
        System.out.print("Ingrese el Rol del Usuario (ADMIN/USER): ");
        String rolInput = scanner.next().toUpperCase();
        Usuario.Rol rol = Usuario.Rol.valueOf(rolInput);
        Usuario usuario = new Usuario(nombre, direccion, email, telefono, rol);
        usuarioService.crearUsuario(usuario);
        System.out.println("Usuario creado exitosamente con rol: " + rol);

    }

    private static void consultarUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
        System.out.println("Lista de Usuarios:");
        usuarios.forEach(usuario -> System.out.println("ID: " + usuario.getIdUsuario() + ", Nombre: " + usuario.getNombre()));
    }

    private static void actualizarUsuario() {
        // Mostrar lista de usuarios existentes
        System.out.println("\n--- Lista de Usuarios Existentes ---");
        consultarUsuarios(); // Llama al método que lista todos los usuarios
        System.out.println("------------------------------------");

        System.out.print("ID de usuario a actualizar: ");
        int id = scanner.nextInt();
        Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
        if (usuario != null) {
            System.out.print("Nueva Dirección: ");
            usuario.setDireccion(scanner.next());
            usuarioService.actualizarUsuario(usuario);
            System.out.println("Usuario actualizado.");
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void eliminarUsuario() {
        System.out.print("ID de usuario a eliminar: ");
        int id = scanner.nextInt();
        usuarioService.eliminarUsuario(id);
        System.out.println("Usuario eliminado.");
    }

    // Caso de uso: Gestionar Centros de Recolección
    private static void gestionarCentros(Usuario usuarioActual) {
        if (usuarioActual.getRol() != Usuario.Rol.ADMIN) {
            System.out.println("Acceso denegado. Solo los administradores pueden gestionar centros de recolección.");
            return;
        }
        System.out.println("\n-------------------- Gestión de Centros de Recolección --------------------");

        // Mostrar lista de centros de recolección existentes
        System.out.println("\n--- Lista de Centros de Recolección Existentes ---");
        consultarCentros(); // Llama al metodo que lista todos los centros
        System.out.println("------------------------------------");

        System.out.println("1. Crear Centro de Recolección");
        System.out.println("2. Consultar Centros de Recolección");
        System.out.println("3. Actualizar Centro de Recolección");
        System.out.println("4. Eliminar Centro de Recolección");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                crearCentro();
                break;
            case 2:
                consultarCentros();
                break;
            case 3:
                actualizarCentro();
                break;
            case 4:
                eliminarCentro();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private static void crearCentro() {
        // Mostrar lista de centros de recolección existentes
        System.out.println("\n--- Lista de Centros de Recolección Existentes ---");
        consultarCentros(); // Llama al metodo que lista todos los centros
        System.out.println("------------------------------------");

        System.out.print("Nombre del Centro: ");
        String nombre = scanner.next();
        System.out.print("Dirección del Centro: ");
        String direccion = scanner.next();
        System.out.print("Capacidad del Centro (en kg): ");
        int capacidad = scanner.nextInt();

        CentroRecoleccion centro = new CentroRecoleccion(nombre, direccion, capacidad);
        centroRecoleccionService.crearCentroRecoleccion(centro);
        System.out.println("Centro de recolección creado exitosamente.");
    }

    private static void consultarCentros() {
        List<CentroRecoleccion> centros = centroRecoleccionService.obtenerTodosLosCentros();
        System.out.println("Lista de Centros de Recolección:");
        centros.forEach(centro -> System.out.println("ID: " + centro.getIdCentro() + ", Nombre: " + centro.getNombre() + ", Dirección: " + centro.getDireccion()));
    }

    private static void actualizarCentro() {
        System.out.print("ID del Centro a actualizar: ");
        int id = scanner.nextInt();
        CentroRecoleccion centro = centroRecoleccionService.obtenerCentroPorId(id);
        if (centro != null) {
            System.out.print("Nueva Capacidad del Centro (en kg): ");
            centro.setCapacidad(scanner.nextInt());
            centroRecoleccionService.actualizarCentro(centro);
            System.out.println("Centro actualizado.");
        } else {
            System.out.println("Centro de recolección no encontrado.");
        }
    }

    private static void eliminarCentro() {
        System.out.print("ID del Centro a eliminar: ");
        int id = scanner.nextInt();
        centroRecoleccionService.eliminarCentro(id);
        System.out.println("Centro de recolección eliminado.");
    }

    // Caso de uso: Gestionar Solicitudes
    private static void gestionarSolicitudes(Usuario usuarioActual) {
        // Verifica que el usuario actual tenga el rol de ADMIN
        if (usuarioActual.getRol() != Usuario.Rol.ADMIN) {
            System.out.println("Acceso denegado. Solo los administradores pueden gestionar solicitudes.");
            return;
        }

        boolean gestionar = true;
        while (gestionar) {
            System.out.println("\n--- Gestión de Solicitudes ---");
            System.out.println("1. Crear Solicitud");
            System.out.println("2. Consultar Solicitudes");
            System.out.println("3. Actualizar Estado de Solicitud");
            System.out.println("4. Eliminar Solicitud");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearSolicitud(); // Método para que el administrador cree una solicitud
                    break;
                case 2:
                    consultarSolicitudes(); // Método para que el administrador consulte todas las solicitudes
                    break;
                case 3:
                    actualizarEstadoSolicitud(); // Método para que el administrador actualice el estado de una solicitud
                    break;
                case 4:
                    eliminarSolicitud(); // Método para que el administrador elimine una solicitud
                    break;
                case 5:
                    gestionar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }


    private static void crearSolicitud() {
        // Mostrar lista de usuarios existentes
        System.out.println("\n--- Lista de Usuarios Existentes ---");
        consultarUsuarios(); // Llama al metodo que lista todos los usuarios
        System.out.println("------------------------------------");

        // Mostrar lista de centros de recolección existentes
        System.out.println("\n--- Lista de Centros de Recolección Existentes ---");
        consultarCentros(); // Llama al metodo que lista todos los centros de recolección
        System.out.println("------------------------------------");

        // Paso 1: Selección de Usuario y Centro de Recolección
        System.out.print("Ingrese el ID del Usuario: ");
        int idUsuario = scanner.nextInt();
        Usuario usuario = usuarioService.obtenerUsuarioPorId(idUsuario);

        System.out.print("Ingrese el ID del Centro de Recolección: ");
        int idCentro = scanner.nextInt();
        CentroRecoleccion centro = centroRecoleccionService.obtenerCentroPorId(idCentro);

        if (usuario != null && centro != null) {
            // Crear una nueva solicitud con estado inicial "Ingresada"
            Solicitud nuevaSolicitud = new Solicitud(usuario, centro, "Ingresada");
            solicitudService.crearSolicitud(nuevaSolicitud);
            System.out.println("Solicitud creada exitosamente con estado 'Ingresada'.");

            // Paso 2: Agregar Tipos de RAEE a la Solicitud
            boolean agregarMasTipos = true;
            while (agregarMasTipos) {
                System.out.println("\n--- Configurar Tipos de RAEE para esta Solicitud ---");
                System.out.println("1. Agregar Tipo de RAEE");
                System.out.println("2. Finalizar Configuración de la Solicitud");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        agregarTipoRAEESolicitud(nuevaSolicitud);
                        break;
                    case 2:
                        agregarMasTipos = false;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
            System.out.println("Configuración de la solicitud completada.");
        } else {
            System.out.println("Usuario o Centro de Recolección no encontrado. No se pudo crear la solicitud.");
        }
    }

    private static void crearSolicitudUsuario(Usuario usuario) {
        // Mostrar lista de centros de recolección disponibles
        System.out.println("\n--- Centros de Recolección Disponibles ---");
        consultarCentros(); // Método que lista todos los centros de recolección
        System.out.println("------------------------------------");

        // Solicitar ID del centro de recolección
        System.out.print("Ingrese el ID del Centro de Recolección para su Solicitud: ");
        int idCentro = scanner.nextInt();
        CentroRecoleccion centro = centroRecoleccionService.obtenerCentroPorId(idCentro);

        if (centro != null) {
            // Crear la solicitud en estado "Ingresada"
            Solicitud nuevaSolicitud = new Solicitud(usuario, centro, "Ingresada");
            solicitudService.crearSolicitud(nuevaSolicitud);
            System.out.println("Solicitud creada exitosamente con estado 'Ingresada'.");

            // Permitir agregar tipos de RAEE a la solicitud
            boolean agregarMasTipos = true;
            while (agregarMasTipos) {
                System.out.println("\n--- Configurar Tipos de RAEE para esta Solicitud ---");
                System.out.println("1. Agregar Tipo de RAEE");
                System.out.println("2. Finalizar Configuración de la Solicitud");
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        agregarTipoRAEESolicitud(nuevaSolicitud);
                        break;
                    case 2:
                        agregarMasTipos = false;
                        System.out.println("Configuración de la solicitud completada.");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
            }
        } else {
            System.out.println("Centro de Recolección no encontrado. No se pudo crear la solicitud.");
        }
    }

    private static void consultarEstadoSolicitudUsuario(Usuario usuario) {
        List<Solicitud> solicitudes = solicitudService.obtenerSolicitudesPorUsuario(usuario.getIdUsuario());

        if (solicitudes.isEmpty()) {
            System.out.println("No tiene solicitudes en el sistema.");
        } else {
            System.out.println("\n--- Estado de sus Solicitudes ---");
            for (Solicitud solicitud : solicitudes) {
                System.out.println("ID Solicitud: " + solicitud.getIdSolicitud() +
                        ", Centro de Recolección: " + solicitud.getCentroRecoleccion().getNombre() +
                        ", Estado: " + solicitud.getEstado());
            }
            System.out.println("------------------------------------");
        }
    }

    private static void consultarSolicitudes() {
        List<Solicitud> solicitudes = solicitudService.obtenerTodasLasSolicitudes();
        if (solicitudes.isEmpty()) {
            System.out.println("No se encontraron solicitudes.");
        } else {
            System.out.println("\nLista de Solicitudes:");
            for (Solicitud solicitud : solicitudes) {
                System.out.println("ID Solicitud: " + solicitud.getIdSolicitud() +
                        ", Usuario: " + solicitud.getUsuario().getNombre() +
                        ", Centro de Recolección: " + solicitud.getCentroRecoleccion().getNombre() +
                        ", Estado: " + solicitud.getEstado());

                // Obtener y mostrar los tipos de RAEE y cantidades asociadas a esta solicitud
                List<CantidadTipo> cantidades = cantidadTipoService.obtenerCantidadesPorSolicitud(solicitud.getIdSolicitud());
                if (cantidades.isEmpty()) {
                    System.out.println("  No contiene tipos de RAEE asociados.");
                } else {
                    System.out.println("  Tipos de RAEE en esta solicitud:");
                    for (CantidadTipo cantidadTipo : cantidades) {
                        System.out.println("    - Tipo: " + cantidadTipo.getTipoRAEE().getDescripcion() +
                                ", Cantidad: " + cantidadTipo.getCantidad());
                    }
                }
                System.out.println("----------------------------------------------------");
            }
        }
    }

    private static void actualizarEstadoSolicitud() {
        // Mostrar lista de solicitudes existentes
        System.out.println("\n--- Lista de Solicitudes Existentes ---");
        consultarSolicitudes(); // Llama al metodo que lista todas las solicitudes
        System.out.println("------------------------------------");

        System.out.print("ID de la Solicitud a actualizar: ");
        int idSolicitud = scanner.nextInt();
        Solicitud solicitud = solicitudService.obtenerSolicitudPorId(idSolicitud);

        if (solicitud != null) {
            System.out.print("Nuevo Estado (Ej: Confirmada, En Proceso, Finalizada): ");
            String nuevoEstado = scanner.next();
            solicitudService.actualizarEstadoSolicitud(solicitud.getIdSolicitud(), nuevoEstado);
            System.out.println("Estado de la solicitud actualizado.");
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }

    private static void eliminarSolicitud() {
        System.out.print("ID de la Solicitud a eliminar: ");
        int id = scanner.nextInt();
        solicitudService.eliminarSolicitud(id);
        System.out.println("Solicitud eliminada.");
    }


    private static void configurarTiposRAEESolicitud() {
        System.out.print("Ingrese el ID de la Solicitud: ");
        int idSolicitud = scanner.nextInt();

        Solicitud solicitud = solicitudService.obtenerSolicitudPorId(idSolicitud);
        if (solicitud == null) {
            System.out.println("Solicitud no encontrada.");
            return;
        }


        boolean configurar = true;
        while (configurar) {
            System.out.println("\n--- Configuración de Tipos de RAEE en Solicitud ---");
            System.out.println("1. Agregar Tipo de RAEE a la Solicitud");
            System.out.println("2. Consultar Tipos de RAEE en la Solicitud");
            System.out.println("3. Eliminar Tipo de RAEE de la Solicitud");
            System.out.println("4. Volver al Menú de Solicitudes");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarTipoRAEESolicitud(solicitud);
                    break;
                case 2:
                    consultarTiposRAEESolicitud(solicitud);
                    break;
                case 3:
                    eliminarTipoRAEESolicitud(solicitud);
                    break;
                case 4:
                    configurar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


    private static void agregarTipoRAEESolicitud(Solicitud solicitud) {
        // Mostrar lista de Tipos de RAEE existentes
        System.out.println("\n--- Lista de Tipos de RAEE Existentes ---");
        consultarTiposRAEE(); // Llama al metodo que lista todas las Tipos de RAEE
        System.out.println("------------------------------------");

        System.out.print("Ingrese el ID del Tipo de RAEE: ");
        int idTipo = scanner.nextInt();
        TipoRAEE tipoRAEE = tipoRAEEService.obtenerTipoRAEEPorId(idTipo);

        if (tipoRAEE != null) {
            System.out.print("Ingrese la Cantidad de este Tipo de RAEE: ");
            float cantidad = scanner.nextFloat();
            CantidadTipo cantidadTipo = new CantidadTipo(tipoRAEE,solicitud,  cantidad);
            cantidadTipoService.agregarTipoRAEEASolicitud(cantidadTipo);
            System.out.println("Tipo de RAEE agregado a la solicitud.");
        } else {
            System.out.println("Tipo de RAEE no encontrado.");
        }
    }

    private static void consultarTiposRAEESolicitud(Solicitud solicitud) {
        List<CantidadTipo> cantidades = cantidadTipoService.obtenerCantidadesPorSolicitud(solicitud.getIdSolicitud());
        if (cantidades.isEmpty()) {
            System.out.println("No se encontraron tipos de RAEE en esta solicitud.");
        } else {
            System.out.println("Tipos de RAEE en la Solicitud:");
            cantidades.forEach(cantidadTipo -> System.out.println(
                    "Tipo: " + cantidadTipo.getTipoRAEE().getDescripcion() +
                            ", Cantidad: " + cantidadTipo.getCantidad()
            ));
        }
    }


    private static void consultarTiposRAEE() {
        List<TipoRAEE> tiposRAEE = tipoRAEEService.obtenerTodosLosTiposRAEE();
        if (tiposRAEE.isEmpty()) {
            System.out.println("No se encontraron tipos de RAEE.");
        } else {
            System.out.println("\nLista de tipos de RAEE:");
            for (TipoRAEE tipoRAEE : tiposRAEE) {
                System.out.println("ID Tipo RAEE: " + tipoRAEE.getIdTipo() +
                        ", Descripción: " + tipoRAEE.getDescripcion());
            }
        }
    }

    private static void eliminarTipoRAEESolicitud(Solicitud solicitud) {
        System.out.print("Ingrese el ID de la CantidadTipo a eliminar: ");
        int idCantidadTipo = scanner.nextInt();
        cantidadTipoService.eliminarCantidadTipo(idCantidadTipo);
        System.out.println("Tipo de RAEE eliminado de la solicitud.");
    }
}
