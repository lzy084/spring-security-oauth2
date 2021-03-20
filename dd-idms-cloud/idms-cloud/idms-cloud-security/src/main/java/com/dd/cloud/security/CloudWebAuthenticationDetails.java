package com.dd.cloud.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class CloudWebAuthenticationDetails extends WebAuthenticationDetails {
    private static final long serialVersionUID = 8522362828655932804L;
    /**
     * The remote address.
     */
    private final String remoteAddress;

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public CloudWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        remoteAddress = getRealAddr(request);

    }

    protected String getRealAddr(final HttpServletRequest request) {

        String customerIp = "";
        // If proxies by nginx apache ...
        String temp1 = request.getHeader("X-Real-IP");

        if (!StringUtils.isBlank(temp1)) {
            customerIp = temp1;
        } else {
            // use default
            customerIp = request.getRemoteAddr();
        }
        return customerIp;
    }

    @Override
    public String getRemoteAddress() {
        return remoteAddress;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof WebAuthenticationDetails) {
            WebAuthenticationDetails rhs = (WebAuthenticationDetails) obj;

            if ((getRemoteAddress() == null) && (rhs.getRemoteAddress() != null)) {
                return false;
            }

            if ((getRemoteAddress() != null) && (rhs.getRemoteAddress() == null)) {
                return false;
            }

            if (getRemoteAddress() != null) {
                if (!getRemoteAddress().equals(rhs.getRemoteAddress())) {
                    return false;
                }
            }

            if ((getSessionId() == null) && (rhs.getSessionId() != null)) {
                return false;
            }

            if ((getSessionId() != null) && (rhs.getSessionId() == null)) {
                return false;
            }

            if (getSessionId() != null) {
                if (!getSessionId().equals(rhs.getSessionId())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode() {
        int code = 1618;

        if (getRemoteAddress() != null) {
            code = code * (getRemoteAddress().hashCode() % 7);
        }

        if (getSessionId() != null) {
            code = code * (getSessionId().hashCode() % 7);
        }

        return code;
    }

}
