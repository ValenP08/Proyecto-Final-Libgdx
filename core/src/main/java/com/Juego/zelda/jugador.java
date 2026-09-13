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
private final Animation<Texture> animacionMovimiento;
private  Texture animacionQuieto= new Texture(Gdx.files.internal("AtaqueDelante 1.png"));
private Texture animacion;
public jugador(float x, float y, Viewport gameViewport, Texture texture, Animation<Texture> animacionAtaque, Animation<Texture> animacionMovimiento)
{
super(x,y,texture.getWidth() * ESCALA, texture.getHeight() * ESCALA,texture);
this.gameViewport = gameViewport;
this.animacionAtaque=animacionAtaque;
    this.animacionMovimiento = animacionMovimiento;
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
    var centroJugador=getCenter(TMP_VEC2);

    ataque.add(new Ataque(centroJugador,ultimaDireccion,animacionAtaque));
tiempoAtaque=cooldownAtaque;
}

move(deltaTime);

    if(!moveDirection.isZero())
    {
    tiempoMovimiento+=deltaTime;
        setTexture(animacionMovimiento.getKeyFrame(tiempoMovimiento, true));
    }

    else{
        tiempoMovimiento = 0;
    setTexture(animacionQuieto);
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
