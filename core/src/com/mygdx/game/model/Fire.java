package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;

public class Fire {
	public static final int EXPLODE=0;
	public static final int DEAD=1;
	
	public Vector2 pos=new Vector2();
	public int state=EXPLODE;
	public float explodeTime=0;
	
	public Fire(float x, float y){
		this.pos.x=x;
		this.pos.y=y;
	}
	
	public void update(float deltaTime){
		if(state==EXPLODE){
			if(explodeTime>1.2f){
				explodeTime=0;
				state=DEAD;
			}
		}
		explodeTime=explodeTime+deltaTime;
	}
}
