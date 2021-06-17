/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rmiserver;

import com.mycompany.rmiinterface.RmiInterface;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ubuntu
 */
public class RmiServer extends UnicastRemoteObject implements RmiInterface{
    
    private final int port = 3232;
    private final String tag = "operation";
    
    public static  void main(String[] args) throws RemoteException{
    
        (new RmiServer()).initServer();
    
    }
    
    public RmiServer() throws RemoteException {
        super();
    }

    @Override
    public int calcularSuma(int x, int y) throws RemoteException {
        return x+y;
    }
    
    public void initServer(){
        try {
            String ipAddress= InetAddress.getLocalHost().toString();
            System.out.println("Escuchando en: " + ipAddress + " :  "+port);
            Registry registry = LocateRegistry.createRegistry(port);
            registry.bind(tag, (RmiInterface)this);
            
        } catch (UnknownHostException | RemoteException | AlreadyBoundException ex) {
            Logger.getLogger(RmiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
