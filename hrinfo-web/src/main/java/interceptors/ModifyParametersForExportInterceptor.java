package interceptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import com.ha.util.Util;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;


public class ModifyParametersForExportInterceptor implements Interceptor {

	public ModifyParametersForExportInterceptor() {
        super();
    }

	public void destroy() {
        // Nothing to do.
    }

	public void init() {
		System.out.println("Initializing ModifyParametersForExportInterceptor..."); 
    }

	public String intercept(final ActionInvocation invocation) throws Exception {
		final String result;

		final ActionContext actionContext = invocation.getInvocationContext();
        final Map<String,Object> parameters = actionContext.getParameters();
        
        String paramToRemove = null;
        
        if (parameters != null) {
        	
        	for(Iterator i = parameters.keySet().iterator(); i.hasNext();) {
        		final String paramKey = (String)i.next();
        		
        		if(paramKey.equalsIgnoreCase("6578706f7274")) {
        			paramToRemove = paramKey;
        			break;
        		}
        	}
        	       	
        	if(Util.isNotEmpty(paramToRemove)) {
        		parameters.remove(paramToRemove);	            
        	}
        }

        result = invocation.invoke();

        return result;

	}
}
