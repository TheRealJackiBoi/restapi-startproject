package dat3.routes;

import dat3.controller.impl.ExceptionController;
import dat3.exception.ApiException;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {

    private final ExceptionController exceptionController = new ExceptionController();
    private int count = 0;

    private final UserRoutesExample userRoutesExample = new UserRoutesExample();

    private final Logger LOGGER = LoggerFactory.getLogger(Routes.class);

    private void requestInfoHandler(Context ctx) {
        String requestInfo = ctx.req().getMethod() + " " + ctx.req().getRequestURI();
        ctx.attribute("requestInfo", requestInfo);
    }

    public EndpointGroup getRoutes(Javalin app) {
        return () -> {
            app.before(this::requestInfoHandler);

            app.routes(() -> {
                path("/", userRoutesExample.getRoutes());
            });

            app.after(ctx -> LOGGER.info(" Request {} - {} was handled with status code {}", count++, ctx.attribute("requestInfo"), ctx.status()));

            app.exception(ConstraintViolationException.class, exceptionController::constraintViolationExceptionHandler);
            app.exception(ApiException.class, exceptionController::apiExceptionHandler);
            app.exception(Exception.class, exceptionController::exceptionHandler);

        };
    }
}