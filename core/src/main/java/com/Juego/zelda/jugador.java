package com.Juego.zelda;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.w3c.dom.Text;

public class jugador extends Objeto{
private static final int VIDA=5;
private static final float ESCALA= 1/32f;
private static final float cooldownAtaque=0.6f;
private final Viewport gameViewport;
private float vida=VIDA;
private final Vector2 moveDirection=new Vector2();
    private final Vector2 ultimaDireccion=new Vector2(1,0);
private static final float SPEED=2f;
private float tiempoAtaque;
private float tiempoMovimiento;
    private static final Array<Texture> movimiento= new Array<Texture>();   //Seguir acá
private static final Array<Ataque> ataque= new Array<Ataque>();
private final Animation<Texture> animacionAtaque;
    private final Animation<Texture> animacionAtaqueArriba;
    private final Animation<Texture> animacionAtaqueIzquierda;
    private final Animation<Texture> animacionAtaqueDerecha;
private final Animation<Texture> animacionMovimiento;
private final Animation<Texture> animacionMovimientoArriba;
    private final Animation<Texture> animacionMovimientoIzquierda;
    private final Animation<Texture> animacionMovimientoDerecha;
private  Texture animacionQuieto= new Texture(Gdx.files.internal("Protagonista con espada y escudo adelante 1.png"));
    private  Texture animacionQuietoArriba= new Texture(Gdx.files.internal("Protagonista con espada y escudo atras 1.png"));
    private  Texture animacionQuietoIzquierda= new Texture(Gdx.files.internal("Protagonista con espada y escudo izquierda 1.png"));
    private  Texture animacionQuietoDerecha= new Texture(Gdx.files.internal("Protagonista con espada y escudo derecha 1.png"));
    int posicionPersonaje=0;
private Texture animacion;
public jugador(float x, float y, Viewport gameViewport, Texture texture, Animation<Texture> animacionAtaque, Animation<Texture> animacionMovimiento, Animation<Texture> animacionMovimientoArriba, Animation<Texture> animacionMovimientoIzquierda, Animation<Texture> animacionMovimientoDerecha, Animation<Texture> animacionAtaqueArriba, Animation<Texture> animacionAtaqueIzquierda, Animation<Texture> animacionAtaqueDerecha)
{
super(x,y,texture.getWidth() * ESCALA, texture.getHeight() * ESCALA,texture);
this.gameViewport = gameViewport;
this.animacionAtaque=animacionAtaque;
    this.animacionAtaqueArriba = animacionAtaqueArriba;
    this.animacionAtaqueIzquierda = animacionAtaqueIzquierda;
    this.animacionAtaqueDerecha = animacionAtaqueDerecha;
    this.animacionMovimiento = animacionMovimiento;
    this.animacionMovimientoArriba = animacionMovimientoArriba;
    this.animacionMovimientoIzquierda = animacionMovimientoIzquierda;
    this.animacionMovimientoDerecha = animacionMovimientoDerecha;
}

public void reset(float x, float y)
{
rectangle.setPosition(x,y);
vida=VIDA;
tiempoAtaque=0;
ataque.clear();
}

@Override
void update(float deltaTime) {
tiempoAtaque-=deltaTime;
if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && tiempoAtaque<=0)
{
    GameScreen.setMostrarTextura(false);
    var centroJugador=getCenter(TMP_VEC2);
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && posicionPersonaje==1)
        {
        ataque.add(new Ataque(centroJugador, ultimaDireccion, animacionAtaque));
        tiempoAtaque = cooldownAtaque;
}
    else if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && posicionPersonaje==2)
    {
        ataque.add(new Ataque(centroJugador, ultimaDireccion, animacionAtaqueIzquierda));
        tiempoAtaque = cooldownAtaque;
    }
        else if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && posicionPersonaje==3)
        {
            ataque.add(new Ataque(centroJugador, ultimaDireccion, animacionAtaqueDerecha));
            tiempoAtaque = cooldownAtaque;
        }
        else if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && posicionPersonaje==4)
        {
            ataque.add(new Ataque(centroJugador, ultimaDireccion, animacionAtaqueArriba));
            tiempoAtaque = cooldownAtaque;
        }


}

else if(tiempoAtaque<=0.05f)
{
    GameScreen.setMostrarTextura(true);
    move(deltaTime);
}
    if(!moveDirection.isZero())
    {
    tiempoMovimiento+=deltaTime;
            if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.A)) {
        setTexture(animacionMovimientoArriba.getKeyFrame(tiempoMovimiento, true));
        posicionPersonaje=4;
    }
    else if(Gdx.input.isKeyPressed(Input.Keys.W) && Gdx.input.isKeyPressed(Input.Keys.D)) {
        setTexture(animacionMovimientoArriba.getKeyFrame(tiempoMovimiento, true));
        posicionPersonaje=4;

    }
    else if(Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.A)) {
        setTexture(animacionMovimiento.getKeyFrame(tiempoMovimiento, true));
        posicionPersonaje=1;
    }
    else if(Gdx.input.isKeyPressed(Input.Keys.S) && Gdx.input.isKeyPressed(Input.Keys.D)) {
        setTexture(animacionMovimiento.getKeyFrame(tiempoMovimiento, true));
        posicionPersonaje=1;
    }
        else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            setTexture(animacionMovimiento.getKeyFrame(tiempoMovimiento, true));
            posicionPersonaje=1;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            setTexture(animacionMovimientoIzquierda.getKeyFrame(tiempoMovimiento, true));
            posicionPersonaje=2;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            setTexture(animacionMovimientoDerecha.getKeyFrame(tiempoMovimiento, true));
            posicionPersonaje=3;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            setTexture(animacionMovimientoArriba.getKeyFrame(tiempoMovimiento, true));
            posicionPersonaje=4;
        }
    }
    else{
        tiempoMovimiento = 0;
        if(posicionPersonaje==1) {
            setTexture(animacionQuieto);
        }
        else if(posicionPersonaje==2) {
            setTexture(animacionQuietoIzquierda);
        }
        else if(posicionPersonaje==3) {
            setTexture(animacionQuietoDerecha);
        }
        else if(posicionPersonaje==4) {
            setTexture(animacionQuietoArriba);
        }
    }
var iterador=ataque.iterator();
while(iterador.hasNext())
{
 var ataque=(Ataque)iterador.next();
 ataque.update(deltaTime);
 if(ataque.Terminado())
 {
iterador.remove();
 }
}


}




    private void move(float deltaTime) {
if(moveDirection.isZero()) return;
float newx=rectangle.getX() + moveDirection.x * SPEED * deltaTime;
float newy=rectangle.getY() + moveDirection.y * SPEED * deltaTime;
newx=MathUtils.clamp(newx,0,gameViewport.getWorldWidth() - rectangle.getWidth());
newy=MathUtils.clamp(newy,0,gameViewport.getWorldHeight() - rectangle.getHeight());
rectangle.setPosition(newx,newy);
}


public void CambiarDireccion(Vector2 Direccion)
{
    if(!Direccion.isZero()){
        ultimaDireccion.set(Direccion);
    }
moveDirection.set(Direccion);
}

    public static Array<Ataque> getAtaque() {
        return ataque;
    }

}
