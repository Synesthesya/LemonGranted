package interfaces;

import graphic.Frame;

import java.util.Observer;

import core.Coordinate;

public interface Controller
{
  public void checkDeployment();

  public void setImage(boolean b, Coordinate c);

  public void setGrids(Frame f);
}
