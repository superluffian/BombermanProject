package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;

public class World {
	public int mapWidth = 19;
	public int mapHeight = 13;
	public int unitSize = 32;
//	public int[][] map = new int[][] {
//			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
//			{ 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 2 },
//			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 },
//			{ 2, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 2 },
//			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 },
//			{ 2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
//			{ 2, 0, 2, 0, 2, 1, 2, 0, 2, 0, 2, 0, 2 },
//			{ 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2 },
//			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 1, 2, 1, 2 },
//			{ 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
//			{ 2, 0, 2, 0, 2, 0, 2, 1, 2, 0, 2, 0, 2 },
//			{ 2, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
//			{ 2, 0, 2, 0, 2, 0, 2, 1, 2, 0, 2, 0, 2 },
//			{ 2, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 2 },
//			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 },
//			{ 2, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 2 },
//			{ 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2, 0, 2 },
//			{ 2, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 2 },
//			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
//		};
	public int[][] map = new int[][] {
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 1, 1, 1, 1, 2, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
		};
	public Player player;
	public Array<Bomb> bombs=new Array<Bomb>();
	public Array<Fire> fires=new Array<Fire>();
	
	public World(){
		player = new Player(this, unitSize, (mapHeight - 2) * unitSize);
		Bomb bomb1=new Bomb(this,3,3);
		Bomb bomb2=new Bomb(this,11,10);
		bombs.add(bomb1);
		bombs.add(bomb2);
	}
	public void update (float delta) {
		checkCollision(player);
		player.update(delta);
		placeBomb();
		for(int i=0;i<bombs.size;i++){
			Bomb bomb=bombs.get(i);
			bomb.update(delta);
		}
		for(int j=0;j<fires.size;j++){
			Fire fire=fires.get(j);
			fire.update(delta);
		}
	}
	
	private void placeBomb(){
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
			Bomb bomb=new Bomb(this, (int) player.position.x/32, (int) player.position.y/32);
			bombs.add(bomb);
		
		}
	}
	
	
	
	private void checkCollision(MovingObject object) {
		if (object.state == MovingObject.State.STAND) {
			object.velocity.set(0, 0);
			return;
		}
		int x = Math.round(object.position.x / unitSize);
		int y = Math.round(object.position.y / unitSize);
		if (object.state == MovingObject.State.MOVING_RIGHT) {
		    if (map[x+1][y] != 0) {
		        if (object.position.x >= x * unitSize) {
		            object.position.x = x * unitSize;
		            object.velocity.set(0, 0);
		        }
		        else {
		        	object.velocity.set(object.speed, 0);
		        }
		    }
		    else {
		        if (object.velocity.y != 0) {
		            if ((object.velocity.y > 0 && object.position.y >= y * unitSize)
		                || (object.velocity.y < 0 && object.position.y <= y * unitSize)) {
		                object.position.y = y * unitSize;
		                object.velocity.set(object.speed, 0);
		            }
		        }
		        else {
		        	if (object.position.y > y * unitSize && object.position.x >= x * unitSize && map[x+1][y+1] != 0) {
		        		object.position.x = x * unitSize;
		        		object.velocity.set(0, -object.speed);
		        	}
		        	else if (object.position.y < y * unitSize && object.position.x >= x * unitSize && map[x+1][y-1] != 0) {
		        		object.position.x = x * unitSize;
		        		object.velocity.set(0, object.speed);
		        	}
		        	else {
		        		object.velocity.set(object.speed, 0);
		        	}
		        }
		    } 
		}
		
		if (object.state == MovingObject.State.MOVING_LEFT) {
		    if (map[x-1][y] != 0) {
		        if (object.position.x <= x * unitSize) {
		            object.position.x = x * unitSize;
		            object.velocity.set(0, 0);
		        }
		        else {
		        	object.velocity.set(-object.speed, 0);
		        }
		    }
		    else {
		        if (object.velocity.y != 0) {
		            if ((object.velocity.y > 0 && object.position.y >= y * unitSize)
		                || (object.velocity.y < 0 && object.position.y <= y * unitSize)) {
		                object.position.y = y * unitSize;
		                object.velocity.set(-object.speed, 0);
		            }
		        }
		        else {
		        	if (object.position.y > y * unitSize && object.position.x <= x * unitSize && map[x-1][y+1] != 0) {
		        		object.position.x = x * unitSize;
		        		object.velocity.set(0, -object.speed);
		        	}
		        	else if (object.position.y < y * unitSize && object.position.x <= x * unitSize && map[x-1][y-1] != 0) {
		        		object.position.x = x * unitSize;
		        		object.velocity.set(0, object.speed);
		        	}
		        	else {
		        		object.velocity.set(-object.speed, 0);
		        	}
		        }
		    } 
		}
		
		if (object.state == MovingObject.State.MOVING_UP) {
		    if (map[x][y+1] != 0) {
		        if (object.position.y >= y * unitSize) {
		            object.position.y = y * unitSize;
		            object.velocity.set(0, 0);
		        }
		        else {
		        	object.velocity.set(0, object.speed);
		        }
		    }
		    else {
		        if (object.velocity.x != 0) {
		            if ((object.velocity.x > 0 && object.position.x >= x * unitSize)
		                || (object.velocity.x < 0 && object.position.x <= x * unitSize)) {
		                object.position.x = x * unitSize;
		                object.velocity.set(0, object.speed);
		            }
		        }
		        else {
		        	if (object.position.x > x * unitSize && object.position.y >= y * unitSize && map[x+1][y+1] != 0) {
		        		object.position.y = y * unitSize;
		        		object.velocity.set(-object.speed, 0);
		        	}
		        	else if (object.position.x < x * unitSize && object.position.y >= y * unitSize && map[x-1][y+1] != 0) {
		        		object.position.y = y * unitSize;
		        		object.velocity.set(object.speed, 0);
		        	}
		        	else {
		        		object.velocity.set(0, object.speed);
		        	}
		        }
		    }
		}
		
		if (object.state == MovingObject.State.MOVING_DOWN) {
		    if (map[x][y-1] != 0) {
		        if (object.position.y <= y * unitSize) {
		            object.position.y = y * unitSize;
		            object.velocity.set(0, 0);
		        }
		        else {
		        	object.velocity.set(0, -object.speed);
		        }
		    }
		    else {
		        if (object.velocity.x != 0) {
		            if ((object.velocity.x > 0 && object.position.x >= x * unitSize)
		                || (object.velocity.x < 0 && object.position.x <= x * unitSize)) {
		                object.position.x = x * unitSize;
		                object.velocity.set(0, -object.speed);
		            }
		        }
		        else {
		        	if (object.position.x > x * unitSize && object.position.y >= y * unitSize && map[x+1][y-1] != 0) {
		        		object.position.y = y * unitSize;
		        		object.velocity.set(-object.speed, 0);
		        	}
		        	else if (object.position.x < x * unitSize && object.position.y >= y * unitSize && map[x-1][y-1] != 0) {
		        		object.position.y = y * unitSize;
		        		object.velocity.set(object.speed, 0);
		        	}
		        	else {
		        		object.velocity.set(0, -object.speed);
		        	}
		        }
		    }
		}
	}
}
