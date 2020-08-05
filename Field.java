package randomGame;

import processing.core.PApplet;

public class Field {
	private PApplet parent;

	private Grass grass[];
	private Tree tree[];

	public Field(PApplet parent) {
		this.parent = parent;

		grass = new Grass[3];

		grass[0] = new Grass(parent);
		grass[1] = new Grass(parent);
		grass[2] = new Grass(parent);

		tree = new Tree[3];

		tree[0] = new Tree(parent);
		tree[1] = new Tree(parent);
		tree[2] = new Tree(parent);
	}

	public void render() {
		parent.pushMatrix();

		// Calculates so that the Field will move when you move the camera
		parent.translate(Camera.location.x, Camera.location.y);

		for (int i = 0; i < 3; i++) {
			grass[i].makeGrass();

			if (placeTreeOnGrass(tree[i], grass[i])) {
				/*
				 * If there is grass where it planned to plant the tree the tree is visable. So
				 * maybe we can make this better in a way so that instead of having the tree not
				 * visable where it isn't grass make it so that it will not even calculate a
				 * tree where it isn't grass?
				 * 
				 * Like make use of all the Objects. Maybe delete the Objects that aren't
				 * visable if there is a way?
				 */
				tree[i].makeTree();
			}
		}

		parent.popMatrix();
	}

	private boolean placeTreeOnGrass(Tree tree, Grass grass) {
		Float[] tempGrass = grass.getGrassAreaPos().toArray(new Float[grass.getGrassAreaPos().size()]);

		if (tree.treePos()[0] > tempGrass[0] && tree.treePos()[1] > tempGrass[1] && tree.treePos()[0] < tempGrass[2]
				&& tree.treePos()[1] < tempGrass[3]) {
			return true;
		}
		return false;
	}
}