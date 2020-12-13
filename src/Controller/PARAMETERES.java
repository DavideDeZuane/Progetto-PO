package Controller;

import java.util.EnumSet;

public enum PARAMETERES {
    ID,
    TYPE,
    COMPANY,
    LOCATION,
    TITLE,
    DESCRIPTION;

    public static final EnumSet<PARAMETERES> parametri = EnumSet.allOf(PARAMETERES.class);
}
