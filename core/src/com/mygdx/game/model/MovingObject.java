package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

public class MovingObject {
	public enum State {STAND, MOVING_LEFT, MOVING_RIGHT, MOVING_UP, MOVING_DOWN, DEAD};
	public World world;
	public float speed = 50f;
	public Vector2 position = new Vector2();
	public Vector2 velocity = new Vector2(0, 0);
	public State state = State.STAND;
	public State direction = State.MOVING_DOWN;
	public float stateTimer = 0;
	public Vector2 objectInitPos; 
	public float cooldownTimer=0;
	
	public MovingObject(World world, float x, float y) {
		this.world=world;
		position.set(x, y);
		this.objectInitPos=new Vector2(x,y);
	}
	
	public void update(float delta) {
		position.mulAdd(velocity, delta);
		stateTimer += delta;
		if (state != State.STAND && state != State.DEAD) {
			direction = state;
		}
		checkBomb(delta);
	}
	public void checkBomb(float deltaTime){
		for(int i=0;i<world.fires.size;i++){
			Fire fire=world.fires.get(i);
			if(fire.pos.x==Math.round(position.x/32)&&fire.pos.y==Math.round(position.y/32)&&fire.state==Fire.EXPLODE){
				position.set(objectInitPos.x, objectInitPos.y);
			}
		}
	}
}
