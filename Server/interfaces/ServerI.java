package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import core.Coordinate;

public interface ServerI extends Remote
{
  public void caricaPlayer() throws RemoteException;
  public boolean registraPlayer() throws RemoteException;
  public int getID() throws RemoteException;
  public void isReady() throws RemoteException;
  public void shot(int ID, Coordinate c) throws RemoteException;
}
