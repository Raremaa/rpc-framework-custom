package com.masaiqi;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author sq.ma
 * @date 2020/1/15 下午3:27
 */
public class RpcNetTransport{

    private String servicePath;

    private RpcNetTransport() {
    }

    public RpcNetTransport(String servicePath) {
        this.servicePath = servicePath;
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        Object result = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        String[] path = servicePath.split(":");

        try {
            socket = new Socket(path[0], Integer.parseInt(path[1]));
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            objectInputStream=new ObjectInputStream(socket.getInputStream());
            result=objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(objectInputStream!=null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
