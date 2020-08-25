package zombiegame;

import processing.core.PVector;

public interface ICreature {
	public void render();
	public void move();
	public void moveToLocation(PVector random);
	public boolean movedToLocation(PVector random);
}