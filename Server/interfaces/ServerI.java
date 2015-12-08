package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerI extends Remote
{
  public void caricaPlayer() throws RemoteException;
  public boolean registraPlayer() throws RemoteException;
  public int getID() throws RemoteException;
}
