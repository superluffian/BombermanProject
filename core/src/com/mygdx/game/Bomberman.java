package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.model.MovingObject;
import com.mygdx.game.model.World;

public class Bomberman extends ApplicationAdapter implements InputProcessor {
	private World world;
	private WorldRenderer renderer;
	private Asearch asearch;
	
	@Override
	public void create () {
		world = new World();
		renderer = new WorldRenderer(world);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		world.update(Gdx.graphics.getDeltaTime());
		Gdx.gl.glClearColor(0.86f, 0.63f, 0.09f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		renderer.render();
	}
	
	@Override
    public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.RIGHT:
			world.player.state = MovingObject.State.MOVING_RIGHT;
			break;
		case Keys.LEFT:
			world.player.state = MovingObject.State.MOVING_LEFT;
			break;
		case Keys.UP:
			world.player.state = MovingObject.State.MOVING_UP;
			break;
		case Keys.DOWN:
			world.player.state = MovingObject.State.MOVING_DOWN;
			break;
		}
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
    	if ((keycode == Keys.RIGHT && world.player.state == MovingObject.State.MOVING_RIGHT)
    			|| (keycode == Keys.LEFT && world.player.state == MovingObject.State.MOVING_LEFT)
    			|| (keycode == Keys.UP && world.player.state == MovingObject.State.MOVING_UP)
    			|| (keycode == Keys.DOWN && world.player.state == MovingObject.State.MOVING_DOWN)) {
    			world.player.state = MovingObject.State.STAND;
    	}
        return true;
    }
	
	@Override
    public boolean keyTyped(char character) {
        return false;
    }
	
	@Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

//	@Override
//    public boolean touchDown(int screenX, int screenY, int pointer, int button) {	
//        return false;
//    }
	
    @Override	
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			  asearch = new Asearch(world.map, (int) world.player.position.x/32, (int) world.player.position.y/32, (int) screenX/32, (int) screenY/32);
					
					if (asearch.resultList[0].getX- asearch.start.getX()  == 1 ){
						world.player.state = MovingObject.State.MOVING_RIGHT;
					}
					else if (resultList[0].getX() - start.getX()  == -1 ){
						world.player.state = MovingObject.State.MOVING_LEFT;
					}
					else if (resultList[0].getY() - start.getY()  == 1 ){
						world.player.state = MovingObject.State.MOVING_UP;
					}
					else if (resultList[0].getY() - start.getY()  == -1 ){
						world.player.state = MovingObject.State.MOVING_DOWN;
					}
					else break; 	
        return true;
    }


    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
	
	@Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
	
	@Override
    public boolean scrolled(int amount) {
        return false;
    }
}
