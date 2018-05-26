package demo.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;

//https://blog.csdn.net/tianyaleixiaowu/article/details/78083269
@Component
public class AccessFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(AccessFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        
        Object token = WebContextUtil.getAccessToken();
        
        logger.info("Pre AccessFilter  token:{}",token);
        
        //校验token
        if (token == null) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            ctx.setResponseBody("No Token. Access Denied");
            return null;
        } else {
            //TODO 根据token获取相应的登录信息，进行校验（略）
        	logger.info("token:{}",token);
        
        	//添加Basic Auth认证信息
            //ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("app01", "*****"));
        	ctx.addZuulRequestHeader("Authorization", "Bearer " + token);
        	
        	 //灰度示例//MetadataAwarePredicate
            RibbonFilterContextHolder.clearCurrentContext();
            if (token.equals("1234567890")) {
                RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "true");
            } else {
                RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "false");
            } 
        }
        return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

//	 private String getBase64Credentials(String username, String password) {
//	        String plainCreds = username + ":" + password;
//	        byte[] plainCredsBytes = plainCreds.getBytes();
//	        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
//	        return new String(base64CredsBytes);
//	    }
}


class WebContextUtil {

    /**
     * 获取当前上下文授权信息
     * @return
     */
    public static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication;
        }
        throw  new AuthenticationServiceException("authentication not found");
    }

    /**
     * 获取当前上下文token的信息
     * @return
     */
    public static OAuth2AuthenticationDetails getDetails(){
        Authentication authentication = getAuthentication();
        if(authentication == null){
            return null;
        }
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
       return details;
    }

    /**
     * 获取当前登入用户账号
     * @return
     */
    public static String getUsername(){
        return getAuthentication().getName();
    }

    /**
     *  获取当前登入用户的访问accessToken
     * @return
     */
    public static String getAccessToken(){
        if(getDetails() == null){
            return null;
        }
        return getDetails().getTokenValue();
    }
}
