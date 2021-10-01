package com.company.gum.command;

// TODO: Auto-generated Javadoc

/**
 * The Class Router.
 */
public final class Router {

    /**
     * The page path.
     */
    private final String pagePath;
    /**
     * The router type.
     */
    private final RouterType routerType;

    /**
     * Instantiates a new router.
     *
     * @param pagePath   the page path
     * @param routerType the router type
     */
    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    /**
     * Gets the page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Gets the router type.
     *
     * @return the router type
     */
    public RouterType getRouterType() {
        return routerType;
    }

    /**
     * The Enum RouterType.
     */
    public enum RouterType {

        /**
         * The forward.
         */
        FORWARD,
        /**
         * The redirect.
         */
        REDIRECT
    }

}
