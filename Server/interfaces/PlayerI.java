package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

import server.Phase;
import core.Coordinate;

public interface PlayerI extends Remote
{
  public boolean getDeployed() throws RemoteException;
  public void setPhase(Phase fase) throws RemoteException;
  public boolean getStatus(Coordinate c) throws RemoteException;
  public void callHit(boolean b, Coordinate c) throws RemoteException;
}
