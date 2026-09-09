package com.Juego.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ControlsScreen extends ScreenAdapter {
	private final Juego juego;
	private final Batch batch ;
	private final BitmapFont font;
	private final Viewport viewport= new ScreenViewport();
	private final GlyphLayout layout=new GlyphLayout();

	public ControlsScreen(Juego juego)
	{
	this.	juego=juego;
	this.batch=juego.getbatch();
	this.font=juego.getfont();
	}
	
	@Override
	public void resize(int width, int height)
	{
	viewport.update(width,height,true);
	}
	
	@Override
	public void render(float delta)
	{
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
		juego.setScreen(new GameScreen(juego));
		dispose();
		
		return;
		}
	ScreenUtils.clear(Color.BLACK);
	float y = viewport.getWorldHeight() / 2 + 100;
	float centrox = viewport.getWorldWidth() / 2;
	viewport.apply();
	batch.setProjectionMatrix(viewport.getCamera().combined);
	batch.begin();
	
	layout.setText(font," -------------- CONTROLES --------------");
	font.draw(batch, layout, centrox - layout.width / 2,  y);
	y-=30;
	
	layout.setText(font," W    /    A    /    S    /    D    -    Movimiento ");
	font.draw(batch, layout, centrox - layout.width / 2,  y);
	y-=30;
	
	layout.setText(font," Click Izquierdo  -  Ataque   /    Click Derecho  -  Arco");
	font.draw(batch, layout, centrox - layout.width / 2,  y);
	y-=30;
	
	layout.setText(font," R  -  Magia");
	font.draw(batch, layout, centrox - layout.width / 2,  y);
	y-=30;
	
	layout.setText(font," E  -  Interactuar");
	font.draw(batch, layout, centrox - layout.width / 2,  y);
	y-=30;
	
	layout.setText(font," Esc  -  Salir");
	font.draw(batch, layout, centrox - layout.width / 2,  y);
	y-=30;
	
	layout.setText(font," --------------------------------------");
	font.draw(batch, layout, centrox - layout.width / 2,  y);
	y-=30;
	
	layout.setText(font,"Presiona  ESPACIO  para  continuar");
	font.draw(batch, layout, centrox - layout.width / 2,  y);


	
	batch.end();
	}
	
}
