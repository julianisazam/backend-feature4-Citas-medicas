package com.feature4.auth_service;

import com.feature4.auth_service.controller.AuthController;
import com.feature4.auth_service.dto.MessageResponse;
import com.feature4.auth_service.dto.SignupRequest;
import com.feature4.auth_service.model.Rol;
import com.feature4.auth_service.model.Usuario;
import com.feature4.auth_service.repository.RolRepository;
import com.feature4.auth_service.repository.UsuarioRepository;
import com.feature4.auth_service.security.jwt.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    // Mocks para las dependencias del controlador
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private RolRepository rolRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtTokenUtil jwtTokenUtil;

    // Inyecta los mocks en el controlador
    @InjectMocks
    private AuthController authController;

    private SignupRequest signupRequest;
    private Rol userRol;

    @BeforeEach
    void setUp() {
        // Objeto de solicitud de registro común para las pruebas
        signupRequest = new SignupRequest();
        signupRequest.setUsername("nuevoUsuario");
        signupRequest.setEmail("nuevo@email.com");
        signupRequest.setPassword("password123");

        // Rol de usuario común
        userRol = new Rol(1L, Rol.RolNombre.ROLE_USER);
    }

    // --- Pruebas para el Registro de Usuarios (`/registro`) ---

    @Test
    void testRegisterUser_Success() {
        // Arrange: Configuración de los mocks
        when(usuarioRepository.existsByUsername(signupRequest.getUsername())).thenReturn(false);
        when(usuarioRepository.existsByEmail(signupRequest.getEmail())).thenReturn(false);
        when(rolRepository.findByNombre(Rol.RolNombre.ROLE_USER)).thenReturn(Optional.of(userRol));
        when(passwordEncoder.encode(signupRequest.getPassword())).thenReturn("encodedPassword");
        // No es necesario mockear save, solo verificar que se llama.

        // Act: Llamada al método del controlador
        ResponseEntity<MessageResponse> response = authController.registerUser(signupRequest);

        // Assert: Verificación de los resultados
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario registrado exitosamente", response.getBody().getMessage());

        // Verifica que el método save fue llamado una vez con cualquier objeto Usuario
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        // Arrange
        when(usuarioRepository.existsByUsername(signupRequest.getUsername())).thenReturn(true);

        // Act
        ResponseEntity<MessageResponse> response = authController.registerUser(signupRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: El nombre de usuario ya existe", response.getBody().getMessage());

        // Verifica que nunca se intentó guardar el usuario
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    void testRegisterUser_EmailAlreadyInUse() {
        // Arrange
        when(usuarioRepository.existsByUsername(signupRequest.getUsername())).thenReturn(false);
        when(usuarioRepository.existsByEmail(signupRequest.getEmail())).thenReturn(true);

        // Act
        ResponseEntity<MessageResponse> response = authController.registerUser(signupRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error: El email ya está en uso", response.getBody().getMessage());

        // Verifica que nunca se intentó guardar el usuario
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
    
    @Test
    void testRegisterUser_WithAdminRole() {
        // Arrange
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        signupRequest.setRoles(roles);

        Rol adminRol = new Rol(2L, Rol.RolNombre.ROLE_ADMIN);
        
        when(usuarioRepository.existsByUsername(anyString())).thenReturn(false);
        when(usuarioRepository.existsByEmail(anyString())).thenReturn(false);
        when(rolRepository.findByNombre(Rol.RolNombre.ROLE_ADMIN)).thenReturn(Optional.of(adminRol));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        // Act
        ResponseEntity<MessageResponse> response = authController.registerUser(signupRequest);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(rolRepository, times(1)).findByNombre(Rol.RolNombre.ROLE_ADMIN);
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    } 
    
}
