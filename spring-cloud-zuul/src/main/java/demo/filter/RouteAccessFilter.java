package demo.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

//https://blog.csdn.net/tianyaleixiaowu/article/details/78083269
//@Component
public class RouteAccessFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(RouteAccessFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
 
        logger.info("Route AccessFilter ######################### ZuulRequestHeaders={}",ctx.getZuulRequestHeaders());
        
        Enumeration<String> names = request.getAttributeNames();
//        while(names.hasMoreElements()){
//        	String name = names.nextElement();
//        	logger.info("attribute {}={} ",name,request.getAttribute(name));
//        }
        
        names = request.getHeaderNames();
        while(names.hasMoreElements()){
        	String name = names.nextElement();
        	logger.info("header {}={} ",name,request.getHeader(name));
        }
        
//        names = request.getParameterNames();
//        while(names.hasMoreElements()){
//        	String name = names.nextElement();
//        	logger.info("parameter {}={} ",name,request.getParameter(name));
//        }
        
        
//        Object token = request.getParameter("token");
//        
//        logger.info("Route AccessFilter  token:{}",token);
        
//        //校验token
//        if (token == null) {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(401);
//            ctx.setResponseBody("No Token. Access Denied");
//            return null;
//        } else {
//            //TODO 根据token获取相应的登录信息，进行校验（略）
//        	logger.info("token:{}",token);
//        
//        	//添加Basic Auth认证信息
//            //ctx.addZuulRequestHeader("Authorization", "Basic " + getBase64Credentials("app01", "*****"));
//        	ctx.addZuulRequestHeader("Authorization", "Bearer " + token);
//        	
//        	 //灰度示例//MetadataAwarePredicate
//            RibbonFilterContextHolder.clearCurrentContext();
//            if (token.equals("1234567890")) {
//                RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "true");
//            } else {
//                RibbonFilterContextHolder.getCurrentContext().add("gated-launch", "false");
//            } 
//        }
        return null;
	}

	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	 private String getBase64Credentials(String username, String password) {
	        String plainCreds = username + ":" + password;
	        byte[] plainCredsBytes = plainCreds.getBytes();
	        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
	        return new String(base64CredsBytes);
	    }
}
