package controller;

import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.fail;

public class ConnectTest {
    private Connect connect;

    @BeforeEach
    public void setUp() {
        try {
            connect = new Connect();
        } catch (SQLException e) {
            fail("Could not establish connection");
        }
    }

    @Test
    @Tag("VPNRequired")
    @DisplayName("Test de connexion à la base de données")
    public void testConnection() {
        Connection connection = connect.getConnection();
        Assertions.assertNotNull(connection, "La connexion ne doit pas être nulle");
    }

    @Test
    @Tag("VPNRequired")
    @DisplayName("Test de déconnexion de la base de données")
    public void testDisconnection() {
        Connection connection = connect.getConnection();
        Assertions.assertDoesNotThrow(() -> connection.close(),
                "La fermeture de la connexion ne doit pas lever d'exception");
    }
}
