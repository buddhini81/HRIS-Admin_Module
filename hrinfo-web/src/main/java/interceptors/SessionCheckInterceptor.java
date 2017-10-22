package interceptors;

import java.util.Map;


import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


public class SessionCheckInterceptor implements Interceptor {

	private Logger logger = LogManager.getLogger(SessionCheckInterceptor.class);

	public void destroy() {
		logger.info("SessionCheckInterceptor destroy() is called...");
		
	}


	public void init() {
		logger.info("SessionCheckInterceptor init() is called...");
	}


	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext context = actionInvocation.getInvocationContext();
		Map<String, Object> sessionMap = context.getSession();	
		if(sessionMap == null || sessionMap.isEmpty() || sessionMap.get("userProfile")==null) {		
			logger.info("session expired...");
		 return "sessionexpired";
		}
		String actionResult = actionInvocation.invoke();
		return actionResult;
		}



}
