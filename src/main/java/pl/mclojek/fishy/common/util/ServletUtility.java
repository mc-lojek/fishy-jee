package pl.mclojek.fishy.common.util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtility {

    /**
     * Gets path info from the request and returns it. No null is returned, instead empty string is used.
     *
     * @param request original servlet request
     * @return path info (not null)
     */
    public static String parseRequestPath(HttpServletRequest request) {
        String path = request.getPathInfo();
        path = path != null ? path : "";
        return path;
    }

}
