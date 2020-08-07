package zombieGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;

public class Tree {
	PApplet parent;

	private Color treeColor;
	private Color leafColor;

	private final int minTreeWidth = 50;
	private final int maxTreeWidth = 100;
	private final int minTreeHeight = 50;
	private final int maxTreeHeight = 100;
	private float randomTreePosX;
	private float randomTreePosY;
	private float randomTreeWidth;
	private float randomTreeHeight;

	private List<Float> randomLeafPosX = new ArrayList<Float>();
	private List<Float> randomLeafPosY = new ArrayList<Float>();
	private boolean generatedLeaves;

	protected Tree(PApplet parent) {
		this.parent = parent;
		
		parent.ellipseMode(PConstants.CENTER);

		treeColor = new Color(165, 50, 50, 200); // Color of the tree
		leafColor = new Color(80, 160, 80); // Color of the leaf

		randomTreePosX = parent.random(parent.width);
		randomTreePosY = parent.random(parent.height);
		randomTreeWidth = parent.random(minTreeWidth, maxTreeWidth);
		randomTreeHeight = parent.random(minTreeHeight, maxTreeHeight);

		generatedLeaves = false;
	}

	/*
	 * Creates a tree, also random leaves around it
	 */
	protected void makeTree() {
		float x, y, r;
		x = treePos()[0];
		y = treePos()[1];
		r = treePos()[2];
		
		parent.noStroke(); // Makes sure there are no line around objects.
		parent.fill(treeColor.getRGB()); // Draws the color of the tree
		parent.ellipse(x, y, r, r); // Draws the ellipse

		// Generates random leaves
		if (!generatedLeaves) {

			for (float yy = y - r; yy < y + r; yy++) {
				for (float xx = x - r; xx < x + r; xx++) {

					// Calculates so that leaves are generated around the tree
					float midCLoop = PApplet.dist(xx, yy, x, y);
					float midCRadius = PApplet.dist(x, y, x, y - r);

					if (midCLoop < midCRadius) {
						int randomRender = (int) parent.random(8);
						if (randomRender < 1) {
							randomLeafPosX.add(xx);
							randomLeafPosY.add(yy);
						}
					}
				}
			}

			generatedLeaves = true;
		}

		// draws random leaves on the tree
		parent.fill(leafColor.getRGB());

		for (int i = 0; i < randomLeafPosX.size(); i++) {
			parent.ellipse(randomLeafPosX.get(i), randomLeafPosY.get(i), 3, 3);
		}
	}

	protected float[] treePos() {
		return new float[] { randomTreePosX, randomTreePosY, randomTreeWidth, randomTreeHeight };
	}
}
