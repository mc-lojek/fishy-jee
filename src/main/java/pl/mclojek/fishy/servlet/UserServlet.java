package pl.mclojek.fishy.servlet;

import pl.mclojek.fishy.common.MimeTypes;
import pl.mclojek.fishy.common.util.ServletUtility;
import pl.mclojek.fishy.dto.GetUserResponse;
import pl.mclojek.fishy.dto.GetUsersResponse;
import pl.mclojek.fishy.entity.User;
import pl.mclojek.fishy.service.UserService;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = UserServlet.Paths.USERS + "/*")
public class UserServlet extends HttpServlet {

    private UserService service;

    public static class Paths {
        public static final String USERS = "/api/users";
    }

    public static class Patterns {
        public static final String USERS = "^/?$";

        public static final String USER = "^/[0-9]+/?$";
    }

    private final Jsonb jsonb = JsonbBuilder.create();

    @Inject
    public UserServlet(UserService service) {
        this.service = service;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = ServletUtility.parseRequestPath(request);
        String servletPath = request.getServletPath();

        if (Paths.USERS.equals(servletPath)) {
            if (path.matches(Patterns.USER)) {
                getUser(request, response);
                return;
            } else if (path.matches(Patterns.USERS)) {
                getUsers(request, response);
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    }

    private void getUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long id = Long.parseLong(ServletUtility.parseRequestPath(request).replaceAll("/", ""));
        Optional<User> user = service.find(id);

        if (user.isPresent()) {
            response.setContentType(MimeTypes.APPLICATION_JSON);
            response.getWriter().write(jsonb.toJson(GetUserResponse.entityToDtoMapper().apply(user.get())));
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void getUsers(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = service.findAll();

        response.setContentType(MimeTypes.APPLICATION_JSON);
        response.getWriter().write(jsonb.toJson(GetUsersResponse.entityToDtoMapper().apply(users)));
    }

}
