package Exception;

import java.io.IOException;

public class JsonMismatch extends IOException {

    /**
     * this exception tells us that the API query result was a Json object instead of a Json array
     */
    public JsonMismatch()
    {
        super("Attempting to read a json object as json array");
    } //non utilizzata poichè già esiste di sistema da jackson

}
