package util;

public class UriParser {

    public static String[] parseURI(String uri) {
        return uri.split("/");
    }
}
