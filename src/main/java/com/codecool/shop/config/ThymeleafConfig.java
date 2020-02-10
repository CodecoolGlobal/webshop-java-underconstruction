package com.codecool.shop.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

/**
 * Thymeleaf configuration.
 */
@WebListener
public class ThymeleafConfig implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
        TemplateEngine engine = templateEngine(sce.getServletContext());
        TemplateEngineUtil.storeTemplateEngine(sce.getServletContext(), engine);
    }

    public void contextDestroyed(ServletContextEvent sce) {
    }

    private TemplateEngine templateEngine(ServletContext servletContext) {
        /*
        Template Engine objects are implementations of the org.thymeleaf.ITemplateEngine
        interface. One of these implementations is offered by the Thymeleaf core:
        org.thymeleaf.TemplateEngine.

        A template resolver is the only required parameter a TemplateEngine needs,
        although there are many others.
         */
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(templateResolver(servletContext));
        return engine;
    }

    private ITemplateResolver templateResolver(ServletContext servletContext) {
        /*
        Template Resolvers are objects that implement an interface from the
        Thymeleaf API called org.thymeleaf.templateresolver.ITemplateResolver.

        These objects are in charge of determining how our templates will be accessed.

        The org.thymeleaf.templateresolver.ServletContextTemplateResolver means that
        we are going to retrieve our template files as resources from the Servlet Context:
        an application-wide javax.servlet.ServletContext object that exists in every
        Java web application, and that resolves resources from the web application root.
         */
        ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(servletContext);
        resolver.setPrefix("/templates/");

        /*
        HTML is the default template mode for ServletContextTemplateResolver,
        but it is good practice to establish it anyway.
         */
        resolver.setTemplateMode(TemplateMode.HTML);

        /*
        If we did this as well:
            resolver.setSuffix(".html");
        then the template name "product/index" would correspond to:
            servletContext.getResourceAsStream("/templates/product/index.html");

        What the skeleton code has in ProductController.doGet:
            engine.process("product/index.html", context, resp.getWriter());
        because this skeleton method doesn't call resolver.setSuffix.
         */
        return resolver;
    }

}