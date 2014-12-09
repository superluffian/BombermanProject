package com.mygdx.game.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Bomb {
	public static final int COUNTINGDOWN=0;
	public static final int DEAD=1;
	
	World world;
	public Vector2 pos=new Vector2();
	Array<Fire> fires=new Array<Fire>();
	public int state=COUNTINGDOWN;
	float stateTime=0;
	float explodeTime=0;
	
	public Bomb(World world, float x, float y){
		this.world=world;
		this.pos.x=x;
		this.pos.y=y;
	}
	
	public void update(float deltaTime){
		if(state==COUNTINGDOWN){
			if(stateTime>3.0f){
				stateTime=0;
				state=DEAD;
				for(int i=0;i<3;i++){
					Vector2 firePos1=new Vector2(pos.x, pos.y+i);
					if(pos.y+i>world.mapWidth-1){
						firePos1.y=world.mapWidth-1;
					}
					if(world.map[(int) firePos1.x][(int) firePos1.y]!=2){
						world.map[(int) firePos1.x][(int) firePos1.y]=0;
						world.fires.add(new Fire(firePos1.x, firePos1.y));
					}
					else{
						break;
					}
					//world.fires.add(new Fire(pos.x, pos.y+i));
					//world.fires.add(new Fire(pos.x+i, pos.y));
				}
				for(int i=0;i<3;i++){
					Vector2 firePos2=new Vector2(pos.x, pos.y-i);
					if(pos.y-i<0){
						firePos2.y=0;
					}
					if(world.map[(int) firePos2.x][(int) firePos2.y]!=2){
						world.map[(int) firePos2.x][(int) firePos2.y]=0;
						world.fires.add(new Fire(firePos2.x, firePos2.y));
					}
					else{
						break;
					}
				}
				for(int i=0;i<3;i++){
					Vector2 firePos3=new Vector2(pos.x+i, pos.y);
					if(pos.x+i>world.mapWidth-1){
						firePos3.x=world.mapWidth-1;
					}
					if(world.map[(int) firePos3.x][(int) firePos3.y]!=2){
						world.map[(int) firePos3.x][(int) firePos3.y]=0;
						world.fires.add(new Fire(firePos3.x, firePos3.y));
					}
					else{
						break;
					}
				}
				for(int i=0;i<3;i++){
					Vector2 firePos4=new Vector2(pos.x-i, pos.y);
					if(pos.x-i<0){
						firePos4.x=0;
					}
					if(world.map[(int) firePos4.x][(int) firePos4.y]!=2){
						world.map[(int) firePos4.x][(int) firePos4.y]=0;
						world.fires.add(new Fire(firePos4.x, firePos4.y));
					}
					else{
						break;
					}
				}
			}
			stateTime=stateTime+deltaTime;
		}
	}
}
