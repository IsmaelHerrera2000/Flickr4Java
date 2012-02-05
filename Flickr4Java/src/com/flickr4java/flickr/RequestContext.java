/*
 * Copyright (c) 2005 Aetrion LLC.
 */

package com.flickr4java.flickr;

import java.util.ArrayList;
import java.util.List;

import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.photos.Extras;

/**
 * A thread local variable used to hold contextual information used in requests.  To get an instance of this class use
 * RequestContext.getRequestContext().  The method will return a RequestContext object which is only usable within the
 * current thread.
 *
 * @author Anthony Eden
 */
public class RequestContext {

    private static RequestContextThreadLocal threadLocal =
            new RequestContextThreadLocal();

    private Auth auth;
    private String sharedSecret;
    private List<Extras> extras;

    /**
     * Get the RequestContext instance for the current Thread.
     *
     * @return The RequestContext
     */
    public static RequestContext getRequestContext() {
        return (RequestContext) threadLocal.get();
    }

    public Auth getAuth() {
        return auth;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    /**
     * Get a shared secret which is used for any calls which require signing.
     *
     * @deprecated Get the secret from {@link Flickr#getSharedSecret()}.
     * @return The shared secret
     */
    @Deprecated
    public String getSharedSecret() {
        return sharedSecret;
    }

    /**
     * Set the shared secret which is used for any calls which require signing.
     *
     * @deprecated Set the secret in {@link Flickr#setSharedSecret(String)}.
     * @param sharedSecret The shared secret
     */
    @Deprecated
    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    /**
     * Get the List of extra return values requested.
     *
     * @return List of extra return values requested
     */
    public List<Extras> getExtras() {
        if (extras == null)
        {
            extras = new ArrayList<Extras>();
        }
        return extras;
    }

    public void setExtras(List<Extras> extras) {
        this.extras = extras;
    }

    private static class RequestContextThreadLocal extends ThreadLocal<RequestContext> {

        @Override
        protected RequestContext initialValue() {
            return new RequestContext();
        }

    }

}