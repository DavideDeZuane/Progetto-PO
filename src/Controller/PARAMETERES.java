package Controller;
import java.util.EnumSet;

/**
 * enumeratore che contiene i filtri che possono essere applicati per la ricerca del lavoro
 */

public enum PARAMETERES {
    TYPE,
    LOCATION,
    LATITUDINE,
    LONGITUDINE,
    DESCRIPTION;

    public static final EnumSet<PARAMETERES> parametri = EnumSet.noneOf(PARAMETERES.class);
}
