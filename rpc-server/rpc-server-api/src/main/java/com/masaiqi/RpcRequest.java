package com.masaiqi;

import java.io.Serializable;

/**
 * @author sq.ma
 * @date 2020/1/15 下午1:47
 */
public class RpcRequest implements Serializable {

    private static final long serialVersionUID = -6097631678442160886L;

    private String className;

    private String methodName;

    private Object[] parameters;

    private String version;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
