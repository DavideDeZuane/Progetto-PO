package Controller;
import java.util.EnumSet;

/**
 * enumeratore che contiene i filtri che possono essere applicati per la ricerca del lavoro
 */

public enum Parameters {
    TYPE,
    LOCATION,
    DESCRIPTION;

    public static EnumSet<Parameters> parametri = EnumSet.noneOf(Parameters.class);
}
