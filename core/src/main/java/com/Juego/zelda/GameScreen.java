package com.Juego.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends ScreenAdapter {
    private static final float WORLD_WIDTH = 16f;
    private static final float WORLD_HEIGHT = 9f;
    private final Juego juego;
    private final Batch batch;
    private final Texture bgdtexture = new Texture(Gdx.files.internal("BG.png"));
    private final Viewport gameViewport = new ExtendViewport(16f, 9f);
    private final Texture playertexture = new Texture(Gdx.files.internal("AtaqueDelante 1.png"));
    private final Array<Texture> texturaMovimiento = CargaMovimiento();
    private final Animation<Texture> Movimiento = new Animation<>(0.1f, texturaMovimiento);
    private final Array<Texture> texturaMovimientoArriba = CargaMovimientoArriba();
    private final Animation<Texture> MovimientoArriba = new Animation<>(0.1f, texturaMovimientoArriba);
    private final Array<Texture> texturaMovimientoIzquierda = CargaMovimientoIzquierda();
    private final Animation<Texture> MovimientoIzquierda = new Animation<>(0.1f, texturaMovimientoIzquierda);
    private final Array<Texture> texturaMovimientoDerecha = CargaMovimientoDerecha();
    private final Animation<Texture> MovimientoDerecha = new Animation<>(0.1f, texturaMovimientoDerecha);
    private final Vector2 inputMovimiento = new Vector2();
    private final Array<Texture> texturaAtaque = CargaAtaque();
    private final Array<Texture> texturaAtaqueArriba = CargaAtaqueArriba();
    private final Array<Texture> texturaAtaqueIzquierda = CargaAtaqueIzquierda();
    private final Array<Texture> texturaAtaqueDerecha = CargaAtaqueDerecha();
    private final Animation<Texture> Ataque = new Animation<>(1 / 32f, texturaAtaque);
    private final Animation<Texture> AtaqueArriba = new Animation<>(1 / 32f, texturaAtaqueArriba);
    private final Animation<Texture> AtaqueIzquierda= new Animation<>(1/32f,texturaAtaqueIzquierda);
    private final Animation<Texture> AtaqueDerecha= new Animation<>(1/32f,texturaAtaqueDerecha);
    private final jugador Jugador=new jugador(WORLD_WIDTH / 2f, WORLD_HEIGHT /2f,gameViewport,playertexture,Ataque,Movimiento,MovimientoArriba,MovimientoIzquierda,MovimientoDerecha,AtaqueArriba,AtaqueIzquierda,AtaqueDerecha);
    private static  boolean mostrarTextura=true;
public GameScreen(Juego juego)
 {
this.juego=juego;
this.batch = juego.getbatch();
bgdtexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

 }

 private void input()
 {
	 inputMovimiento.setZero();
	 if(Gdx.input.isKeyPressed(Input.Keys.W))
	 {
		inputMovimiento.y+=1;
	 }
	 if(Gdx.input.isKeyPressed(Input.Keys.S))
	 {
		inputMovimiento.y-=1;
	 }
	 if(Gdx.input.isKeyPressed(Input.Keys.A))
	 {
		inputMovimiento.x-=1;
	 }
	 if(Gdx.input.isKeyPressed(Input.Keys.D))
	 {
		inputMovimiento.x+=1;
	 }


     inputMovimiento.nor();
	 Jugador.CambiarDireccion(inputMovimiento);

 }


	public void resize(int width, int height)
	{
	gameViewport.update(width,height,true);
	}

	@Override
	public void show()
	{
resetGame();
	}

	private void resetGame()
	{
	Jugador.reset(WORLD_WIDTH / 2, WORLD_HEIGHT / 2);
	}

    private Array<Texture> CargaAtaque()
    {
   var texturas=new Array<Texture>();

   for(int i=1;i<=5;i++)
   {
    texturas.add(new Texture(Gdx.files.internal(String.format("AtaqueDelante%2d.png",i))));
   }

   return texturas;
    }

   private Array<Texture> CargaAtaqueArriba()
    {
        var texturas=new Array<com.badlogic.gdx.graphics.Texture>();

        for(int i=1;i<=5;i++)
        {
            texturas.add(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal(String.format("Protagonista atacando atras%2d.png",i))));
        }

        return texturas;
    }

    private Array<Texture> CargaAtaqueIzquierda()
    {
        var texturas=new Array<com.badlogic.gdx.graphics.Texture>();

        for(int i=1;i<=4;i++)
        {
            texturas.add(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal(String.format("Protagonista atacando izquierda%2d.png",i))));
        }

        return texturas;
    }

    private Array<Texture> CargaAtaqueDerecha()
    {
        var texturas=new Array<com.badlogic.gdx.graphics.Texture>();

        for(int i=1;i<=4;i++)
        {
            texturas.add(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal(String.format("Protagonista atacando derecha%2d.png",i))));
        }

        return texturas;
    }

    private Array<Texture> CargaMovimiento() {
    var texturas=new Array<Texture>();
        for(int i=1;i<=6;i++)
        {
            texturas.add(new Texture(Gdx.files.internal(String.format("protagonista con espada y escudo adelante%2d.png",i))));
        }
        return texturas;
    }

    private Array<Texture> CargaMovimientoArriba() {
        var texturas=new Array<Texture>();
        for(int i=1;i<=6;i++)
        {
            texturas.add(new Texture(Gdx.files.internal(String.format("protagonista con espada y escudo atras%2d.png",i))));
        }
        return texturas;
    }

    private Array<Texture> CargaMovimientoIzquierda() {
        var texturas=new Array<Texture>();
        for(int i=1;i<=6;i++)
        {
            texturas.add(new Texture(Gdx.files.internal(String.format("protagonista con espada y escudo izquierda%2d.png",i))));
        }
        return texturas;
    }

    private Array<Texture> CargaMovimientoDerecha() {
        var texturas=new Array<Texture>();
        for(int i=1;i<=6;i++)
        {
            texturas.add(new Texture(Gdx.files.internal(String.format("protagonista con espada y escudo derecha%2d.png",i))));
        }
        return texturas;
    }


	@Override
	public void render(float delta)
	{
	input();
    updateLogic(delta);

	ScreenUtils.clear(Color.BLACK);
	gameViewport.apply();
	batch.setProjectionMatrix(gameViewport.getCamera().combined);
	batch.begin();
	drawBackground();

        if(mostrarTextura==true) {
            Jugador.draw(batch);
        }

        for(Ataque ataque : jugador.getAtaque()){
            ataque.draw(batch);
        }



        batch.end();
	}

    private void updateLogic(float delta) {
        Jugador.update(delta);
    }



    public void drawBackground()
	{
		float u2=gameViewport.getWorldWidth() / WORLD_WIDTH;
		float v2=gameViewport.getWorldHeight() / WORLD_HEIGHT;
		batch.draw(bgdtexture,0,0,gameViewport.getWorldWidth(),gameViewport.getWorldHeight(),0,0,u2,v2);
	}
    @Override
    public void dispose() {
    bgdtexture.dispose();
    playertexture.dispose();
    texturaAtaque.forEach(Texture::dispose);
    }

    public static void setMostrarTextura(boolean estado) {
        mostrarTextura = estado;
        System.out.println(mostrarTextura);
    }
}
