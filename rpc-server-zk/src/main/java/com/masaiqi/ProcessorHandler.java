package com.masaiqi;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

/**
 * @author sq.ma
 * @date 2020/1/15 下午2:08
 */
public class ProcessorHandler implements Runnable {

    private Socket socket;
    private Map<String, Object> handlerMap;

    private ProcessorHandler() {
    }

    public ProcessorHandler(Socket socket, Map<String, Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
    }

    @Override
    public void run() {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();

            //调用本地的实现类
            Object result = this.invoke(rpcRequest);

            //写会给客户端
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Object invoke(RpcRequest rpcRequest) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        String serviceName = rpcRequest.getClassName();
        String version = rpcRequest.getVersion();

        if (!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }

        //获取对于名称的接口的实现类
        Object service = handlerMap.get(serviceName);
        if (service == null) {
            throw new RuntimeException("service not found: " + serviceName);
        }

        Object[] args = rpcRequest.getParameters();
        Method method = null;
        Class clazz = Class.forName(rpcRequest.getClassName());
        if (args != null && args.length != 0) {
            Class<?>[] types = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                types[i] = args[i].getClass();
            }
            method = clazz.getMethod(rpcRequest.getMethodName(), types);
        } else {
            method = clazz.getMethod(rpcRequest.getMethodName());
        }
        return method.invoke(service, args);
    }

}
