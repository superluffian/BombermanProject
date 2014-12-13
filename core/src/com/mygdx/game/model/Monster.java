package com.mygdx.game.model;

import search.Asearch;

import com.badlogic.gdx.graphics.Texture;
//import com.mygdx.game.model.MovingObject.State;

public class Monster extends MovingObject{
	private Texture[] tex_down = {new Texture("Monster-0-0.png"), new Texture("Monster-0-4.png"), new Texture("Monster-0-8.png")};
	private Texture[] tex_up = {new Texture("Monster-0-3.png"), new Texture("Monster-0-7.png"), new Texture("Monster-0-11.png")};
	private Texture[] tex_right = {new Texture("Monster-0-2.png"), new Texture("Monster-0-6.png"), new Texture("Monster-0-10.png")};
	private Texture[] tex_left = {new Texture("Monster-0-1.png"), new Texture("Monster-0-5.png"), new Texture("Monster-0-9.png")};
	public World world;
	private float directionTimer=0;
	private Asearch asearch;
	
	public Monster(World world, float x, float y) {
		super(world, x, y);
	}
	
	public Texture getFrame() {
		Texture[] textures = null;
		switch(direction) {
		case MOVING_DOWN:
			textures = tex_down;
			break;
		case MOVING_UP:
			textures = tex_up;
			break;
		case MOVING_RIGHT:
			textures = tex_right;
			break;
		case MOVING_LEFT:
			textures = tex_left;
			break;
		default:
			textures = tex_down;
			break;
		}
		int i = (int)(stateTimer / 0.1f) % 3;
		if (state == State.STAND) {
			return textures[0];
		}
		else {
			return textures[i];
		}
	}
	public void directionUpdate(float deltaTime, int playerX, int playerY, int monsterX, int monsterY){
	
	      	asearch = new Asearch(world.map, playerX, playerY, monsterX, monsterY);
		    int flag = asearch.search(asearch.start.getX(), asearch.start.getY(), asearch.end.getX(), asearch.end.getY());
    	    if (flag == 1){
				if (asearch.resultList.get(1).getX() - asearch.start.getX() == 1 ){
					state=State.MOVING_RIGHT;
					direction=state;				
				}
				else if (asearch.resultList.get(1).getX() - asearch.start.getX()  == -1 ){
					state=State.MOVING_LEFT;
					direction=state;
				}
				else if (asearch.resultList.get(1).getY() - asearch.start.getY()  == 1 ){
					state=State.MOVING_UP;
					direction=state;
				}
				else if (asearch.resultList.get(1).getY() - asearch.start.getY()  == -1 ){
					world.player.state = MovingObject.State.MOVING_DOWN;					
				}
				else world.player.state = MovingObject.State.STAND;
	    	    }
	    	    else world.player.state = MovingObject.State.STAND;
			}
    	    
//			double dir=Math.random();
//			if(dir<0.25f){
//				state=State.MOVING_DOWN;
//				direction=state;
//			}
//			else if(dir<0.5f){
//				state=State.MOVING_LEFT;
//				direction=state;
//			}
//			else if (dir<0.75f){
//				state=State.MOVING_RIGHT;
//				direction=state;
//			}
//			else{
//				state=State.MOVING_UP;
//				direction=state;
//			}	
//			directionTimer=0;
//		}
//		else{
//			directionTimer=directionTimer+deltaTime;
//		}
	}
}
