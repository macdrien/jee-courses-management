package fr.utbm.jee_courses_management.util;

import fr.utbm.jee_courses_management.entity.Client;

public class ClientUtils {

    public static final Integer ID = 1;

    public static final String FIRSTNAME = "first";

    public static final String LASTNAME = "last";

    public static final String ADDRESS = "42 test road";

    public static final String PHONE = "0123456789";

    public static final String EMAIL = "test@test.test";

    public static final Client CLIENT = new Client(ID, FIRSTNAME, LASTNAME, ADDRESS, PHONE, EMAIL, null);
}
