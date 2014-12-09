package com.mygdx.game.model;

//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Vector2;

public class Player extends MovingObject {
	private Texture[] tex_down = {new Texture("player_down_1.png"), new Texture("player_down_2.png"), new Texture("player_down_3.png")};
	private Texture[] tex_up = {new Texture("player_up_1.png"), new Texture("player_up_2.png"), new Texture("player_up_3.png")};
	private Texture[] tex_right = {new Texture("player_right_1.png"), new Texture("player_right_2.png"), new Texture("player_right_3.png")};
	private Texture[] tex_left = {new Texture("player_left_1.png"), new Texture("player_left_2.png"), new Texture("player_left_3.png")};
	public World world;
	
	public Player(World world, float x, float y) {
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
}
