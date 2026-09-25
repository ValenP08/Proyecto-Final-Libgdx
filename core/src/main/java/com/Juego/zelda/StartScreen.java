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

public class StartScreen extends ScreenAdapter {
    private final Juego juego;
    private final Batch batch ;
    private final BitmapFont font;
    private final Viewport viewport= new ScreenViewport();
    private final GlyphLayout layout=new GlyphLayout();
    private static final float WORLD_WIDTH = 16f;
    private static final float WORLD_HEIGHT = 9f;
    private Texture background=new Texture(Gdx.files.internal("Intro Adventure Quest 1.png"));
    private final Array<Texture> texturaFondo =CargaFondo();
    private final Animation<Texture> fondo = new Animation<>(0.4f, texturaFondo);
    private final Viewport gameViewport = new ExtendViewport(16f, 9f);
    private float tiempoMovimiento;

    public StartScreen(Juego juego)
    {
        this.	juego=juego;
        this.batch=juego.getbatch();
        this.font=juego.getfont();
    }


    private Array<Texture> CargaFondo()
    {
        var texturas=new Array<Texture>();

        for(int i=1;i<=5;i++)
        {
            texturas.add(new Texture(Gdx.files.internal(String.format("Intro Adventure Quest%2d.png",i))));
        }

        return texturas;
    }


    public void resize(int width, int height)
    {
        viewport.update(width,height,true);
    }




    @Override
    public void render(float deltaTime) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
        {
         juego.setScreen(new MenuScreen(juego));
         dispose();
         return;
        }

                tiempoMovimiento+=deltaTime;
        ScreenUtils.clear(Color.BLACK);
        float u2=gameViewport.getWorldWidth() / WORLD_WIDTH;
        float v2=gameViewport.getWorldHeight() / WORLD_HEIGHT;
        float y = viewport.getWorldHeight() / 2 + 100;
        float centrox = viewport.getWorldWidth() / 2;
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        background=fondo.getKeyFrame(tiempoMovimiento,true);
        batch.draw(background, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());

        font.getData().setScale(2);
        y-=500;
            layout.setText(font, " Presione [ESPACIO] para continuar");
            font.draw(batch, layout, centrox - layout.width / 2 + 450, y);
        font.getData().setScale(1);
        batch.end();
    }

    public void dispose()
    {
        texturaFondo.forEach(Texture::dispose);
    }
}
