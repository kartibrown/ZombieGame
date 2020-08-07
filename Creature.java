package zombieGame;

import java.awt.Color;

import processing.core.PVector;

public class Creature {

	// Creature stats
	protected int health = 100;

	// Creature pos
	protected PVector location;
	protected PVector look;
	protected PVector randomLocation;
	protected boolean randomLocationGenerated;

	protected Color color;

	protected int speed;
}