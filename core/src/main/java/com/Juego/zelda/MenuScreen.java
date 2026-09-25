package com.Juego.zelda;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen extends ScreenAdapter {
    private final Juego juego;
    private final Batch batch ;
    private final BitmapFont font;
    private final Viewport viewport= new ScreenViewport();
    private final GlyphLayout layout=new GlyphLayout();
    private static final float WORLD_WIDTH = 16f;
    private static final float WORLD_HEIGHT = 9f;
    private final Viewport gameViewport = new ExtendViewport(16f, 9f);
    private Texture backgroundMenu=new Texture(Gdx.files.internal("menu 1.png"));
    private final Array<Texture> texturaFondo =CargaFondoMenu();
    private final Animation<Texture> fondo = new Animation<>(0.6f, texturaFondo);
    public int opcion=1;
    public float tiempoTitileo;
    private float tiempoMovimiento;

    public MenuScreen(Juego juego) {
        this.juego = juego;
        this.batch = juego.getbatch();
        this.font = juego.getfont();
    }

    private Array<Texture> CargaFondoMenu()
    {
        var texturas=new Array<Texture>();

        for(int i=1;i<=6;i++)
        {
            texturas.add(new Texture(Gdx.files.internal(String.format("menu%2d.png",i))));
        }

        return texturas;
    }

    public void resize(int width, int height) {viewport.update(width,height,true);}

    public void render(float delta) {
        tiempoTitileo++;
        tiempoMovimiento+=delta;
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && opcion == 1) {
            juego.setScreen(new GameScreen(juego));
            dispose();
            return;
        }
        ScreenUtils.clear(Color.BLACK);
        float u2 = gameViewport.getWorldWidth() / WORLD_WIDTH;
        float v2 = gameViewport.getWorldHeight() / WORLD_HEIGHT;
        float y = viewport.getWorldHeight() / 2 + 100;
        float centrox = viewport.getWorldWidth() / 2;
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        font.getData().setScale(2f);
        backgroundMenu=fondo.getKeyFrame(tiempoMovimiento,true);
        batch.draw(backgroundMenu, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        y-=150;

            font.setColor(Color.WHITE);
            layout.setText(font, "INICIAR PARTIDA");
            font.draw(batch, layout, centrox - layout.width / 2, y);
            y -= 90;
            layout.setText(font, "CARGAR PARTIDA");
            font.draw(batch, layout, centrox - layout.width / 2, y);
            y -= 90;
            layout.setText(font, "OPCIONES");
            font.draw(batch, layout, centrox - layout.width / 2, y);
            y -= 90;
            layout.setText(font, "SALIR");
            font.draw(batch, layout, centrox - layout.width / 2, y);

        if(Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(opcion>1) {
                opcion--;
                System.out.println(opcion);
                tiempoTitileo=100;
            }
        }
        else if(Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if(opcion<4) {
                opcion++;
                System.out.println(opcion);
                tiempoTitileo=100;
            }
        }
        switch(opcion){
            case 1:
                font.setColor(Color.CORAL);
                y+=270;
                if(tiempoTitileo>100 && tiempoTitileo<200) {
                    layout.setText(font, ">INICIAR PARTIDA<");
                    font.draw(batch, layout, centrox - layout.width / 2, y);
                }
                break;

            case 2:
                font.setColor(Color.CORAL);
                y+=180;
                if(tiempoTitileo>100 && tiempoTitileo<200) {
                layout.setText(font, ">CARGAR PARTIDA<");
                font.draw(batch, layout, centrox - layout.width / 2, y);
            }
                break;

            case 3:
                font.setColor(Color.CORAL);
                y+=90;
                if(tiempoTitileo>100 && tiempoTitileo<200) {
                layout.setText(font, ">OPCIONES<");
                font.draw(batch, layout, centrox - layout.width / 2, y);
            }
                break;
            case 4:
                font.setColor(Color.CORAL);
                if(tiempoTitileo>100 && tiempoTitileo<200) {
                    layout.setText(font, ">SALIR<");
                    font.draw(batch, layout, centrox - layout.width / 2, y);
                }
                if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                    Gdx.app.exit();
                }
                break;
        }
        if(tiempoTitileo>=200){
            tiempoTitileo=0;
        }

        batch.end();
    }




}
