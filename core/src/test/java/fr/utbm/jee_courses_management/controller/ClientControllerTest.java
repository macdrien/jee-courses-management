package fr.utbm.jee_courses_management.controller;

import fr.utbm.jee_courses_management.entity.Client;
import fr.utbm.jee_courses_management.service.ClientService;
import fr.utbm.jee_courses_management.util.ClientUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/** Unit tests for {@link ClientController} */
public class ClientControllerTest {

    /** The {@link ClientController} to test */
    private static ClientController controller;

    /** A mock of {@link ClientService} used by controller */
    private static ClientService service;

    /** Initialize controller and service */
    @BeforeAll
    static void init() {
        service = Mockito.mock(ClientService.class);
        controller = new ClientController(service);
    }

    /** Unit test of {@link ClientController#registerClient(Client)} */
    @Test
    public void testRegisterClientWithSuccess() {
        when(service.registerClient(eq(ClientUtils.CLIENT))).thenReturn(ClientUtils.CLIENT);
        assertEquals(ClientUtils.CLIENT, controller.registerClient(ClientUtils.CLIENT));
    }
}
