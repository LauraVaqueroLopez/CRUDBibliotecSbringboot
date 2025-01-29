package org.example.crudusuario.CRUD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear usuario
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        // Inicializar las colecciones para evitar el proxy de Hibernate
        usuarios.forEach(usuario -> usuario.getPrestamos().size());
        return usuarios;
    }

    // Obtener usuario por id
    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    // Actualizar usuario
    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        if (usuarioRepository.existsById(id)) {
            usuarioActualizado.setId(id);
            return usuarioRepository.save(usuarioActualizado);
        }
        return null; // o lanzar una excepción si no existe el usuario
    }

    // Eliminar usuario
    public boolean eliminarUsuario(Integer id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false; // o lanzar una excepción si no existe el usuario
    }
}
