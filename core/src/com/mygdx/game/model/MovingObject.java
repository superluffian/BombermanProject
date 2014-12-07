package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

public class MovingObject {
	public enum State {STAND, MOVING_LEFT, MOVING_RIGHT, MOVING_UP, MOVING_DOWN, DEAD};
	public World world;
	public float speed = 96f;
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2(0, 0);
	public State state = State.STAND;
	public State direction = State.MOVING_DOWN;
	public float stateTimer = 0;
	
	public MovingObject(World world, float x, float y) {
		this.world=world;
		position.set(x, y);
	}
	
	public void update(float delta) {
		position.mulAdd(velocity, delta);
		stateTimer += delta;
		if (state != State.STAND && state != State.DEAD) {
			direction = state;
		}
	}
	public void checkBomb(float deltaTime){
		
	}
}
