package com.Juego.zelda;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Juego extends Game {

    private Batch batch;
    private BitmapFont font;
    @Override
    public void create() {
    	batch= new SpriteBatch();
    	var generator =new FreeTypeFontGenerator(Gdx.files.internal("error.ttf")); 
    	var fontparams= new FreeTypeFontGenerator.FreeTypeFontParameter();
    	fontparams.size=22;
    	fontparams.color=Color.WHITE;
    	font = generator.generateFont(fontparams);
    	
    	generator.dispose();
    	setScreen(new ControlsScreen(this));
    }



    @Override
    public void dispose() {
    super.dispose();
    batch.dispose();
    font.dispose();
    }
    
    public Batch getbatch()
    {
    	return batch;
    }
    
    public BitmapFont getfont()
    {
    	return font;
    }
}
