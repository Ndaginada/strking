package com.kkai.oauth.filter;

import com.kkai.oauth.enity.Audience;
import com.kkai.oauth.enity.RespStatus;
import com.kkai.oauth.util.JwtHelper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Kkai on 2018/1/30.
 */
public class JsonWebTokenFilter extends ZuulFilter {
    @Autowired
    private Audience audience;

    private static final Logger logger = LoggerFactory.getLogger(JsonWebTokenFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String auth = request.getHeader("Authorization");

        if ((auth != null) && (auth.length() > 7)) {
            String HeadStr = auth.substring(0, 6).toLowerCase();
            if (HeadStr.compareTo("bearer") == 0) {

                auth = auth.substring(7, auth.length());
                if (JwtHelper.parseJWT(auth, audience.getBase64Secret()) != null) {
                    return null;
                }
            }
        }
        ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(RespStatus.UNAUTHORIZED.code());
        ctx.setResponseBody("{\"message\":\"用户未授权！\"}");
        ctx.getResponse().setContentType("application/json;charset=UTF-8");


        return null;
    }
}
