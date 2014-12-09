package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.model.World;
import com.mygdx.game.model.Bomb;
import com.mygdx.game.model.Fire;

public class WorldRenderer {
	private World world;
	private SpriteBatch batch;
	private Texture tex_tile = new Texture("tile.png");
	private Texture tex_brick = new Texture("brick.png");
	private Texture tex_wall = new Texture("wall.png");
	private Texture tex_bomb = new Texture("bomb.png");
	private Animation tex_fire;
	private int unitSize;
	
	public WorldRenderer(World world) {
		this.world = world;
		this.unitSize = world.unitSize;
		batch = new SpriteBatch();
		createAnimation();
	}
	
	private void createAnimation(){
		Texture fireTexture=new Texture(Gdx.files.internal("Fire2small.png"));
		TextureRegion[][] split=new TextureRegion(fireTexture).split(32, 32);
		tex_fire=new Animation(0.1f, split[0][0],split[0][1],split[0][2],split[0][3],split[0][4],split[1][0],split[1][1],split[1][2],split[1][3],split[1][4],split[2][0],split[2][1]);
		//tex_fire= split[0][0];
	}
	
	public void render() {
		batch.begin();
		drawMap();
		batch.draw(world.player.getFrame(), world.player.position.x, world.player.position.y);
		for(int i=0;i<world.monsters.size;i++){
			batch.draw(world.monsters.get(i).getFrame(), world.monsters.get(i).position.x, world.monsters.get(i).position.y);
		}
		renderBomb();
		renderFire();
		batch.end();
	}
	
	private void renderBomb(){
		for(int i=0;i<world.bombs.size;i++){
			Bomb bomb=world.bombs.get(i);
			if(bomb.state==Bomb.COUNTINGDOWN){
				batch.draw(tex_bomb, 32*bomb.pos.x, 32*bomb.pos.y);
			}
		}
	}
	
	private void renderFire(){
		for(int i=0;i<world.fires.size;i++){
			Fire fire=world.fires.get(i);
			if(fire.state==Fire.EXPLODE){
				TextureRegion frame=this.tex_fire.getKeyFrame(fire.explodeTime, false);
				batch.draw(frame, 32*fire.pos.x, 32*fire.pos.y, 32, 32);
				//System.out.println(fire.pos.x);
				//System.out.println(fire.pos.y);
			}
		}
	}
	private void drawMap() {
		for (int x = 0; x < world.mapWidth; x++) {
			for (int y = 0; y < world.mapHeight; y++) {
				if (world.map[x][y] == 0) {
					batch.draw(tex_tile, unitSize * x, unitSize * y);
				}
				else if (world.map[x][y] == 1) {
					batch.draw(tex_brick, unitSize * x, unitSize * y);
				}
				else if (world.map[x][y] == 2) {
					batch.draw(tex_wall, unitSize * x, unitSize * y);
				}
			}
		}
	}
}
