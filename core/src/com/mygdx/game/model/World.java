package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
//import com.badlogic.gdx.math.Vector2;
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
	public Array<Monster> monsters=new Array<Monster>();
	public Array<Bomb> bombs=new Array<Bomb>();
	public Array<Fire> fires=new Array<Fire>();
	
	public World(){
		player = new Player(this, unitSize, (mapHeight - 2) * unitSize);
		for(int i=0;i<100;i++){
			Monster monster = new Monster(this, (mapWidth-2)*unitSize, unitSize);
			monsters.add(monster);
		}
		//Bomb bomb1=new Bomb(this,3,3);
		//Bomb bomb2=new Bomb(this,11,10);
		//bombs.add(bomb1);
		//bombs.add(bomb2);
	}
	public void update (float delta) {
		checkCollision(player);
		player.update(delta);
		placeBomb(delta);
		
		for(int i=0;i<monsters.size;i++){
			checkCollision(monsters.get(i));
			monsters.get(i).directionUpdate(delta, (int) player.position.x/32 ,(int) player.position.y/32, (int) monsters.position.x(i)/32,(int) monsters.position.y(i)/32);
			monsters.get(i).update(delta);
		}
		//monster.directionUpdate(delta);
		//System.out.println(Math.round(monster.position.x/32));
		//System.out.println(Math.round(monster.position.y/32));
		
		for(int i=0;i<bombs.size;i++){
			Bomb bomb=bombs.get(i);
			bomb.update(delta);
		}
		for(int j=0;j<fires.size;j++){
			Fire fire=fires.get(j);
			fire.update(delta);
		}
	}
	
	private void placeBomb(float deltaTime){
		//float cooldownTime=0;
		if(player.cooldownTimer==0&&!ifBomb(Math.round(player.position.x/32), Math.round(player.position.y/32))){
			if(Gdx.input.isKeyPressed(Keys.SPACE)){
				Bomb bomb=new Bomb(this, Math.round(player.position.x/32), Math.round(player.position.y/32));
				bombs.add(bomb);
				player.cooldownTimer=0.2f;
			}
		}
		else{
			if(player.cooldownTimer>0){
				player.cooldownTimer=player.cooldownTimer-deltaTime;
			}
			else if(player.cooldownTimer<0){
				player.cooldownTimer=0;
			}
		}
		//System.out.println(player.cooldownTimer);
	}
	
	private boolean ifBomb(int x, int y){
		for(int i=0;i<bombs.size;i++){
			Bomb bomb=bombs.get(i);
			if(bomb.pos.x==x&&bomb.pos.y==y&&bomb.state==Bomb.COUNTINGDOWN){
				return true;
			}
		}
		return false;
	}
	
	private void checkCollision(MovingObject object) {
		if (object.state == MovingObject.State.STAND) {
			object.velocity.set(0, 0);
			return;
		}
		int x = Math.round(object.position.x / unitSize);
		int y = Math.round(object.position.y / unitSize);
		
		if (object.state == MovingObject.State.MOVING_RIGHT) {
		    if (map[x+1][y] != 0||ifBomb(x+1, y)) {
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
		    if (map[x-1][y] != 0||ifBomb(x-1,y)) {
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
		    if (map[x][y+1] != 0||ifBomb(x,y+1)) {
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
		    if (map[x][y-1] != 0||ifBomb(x, y-1)) {
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
