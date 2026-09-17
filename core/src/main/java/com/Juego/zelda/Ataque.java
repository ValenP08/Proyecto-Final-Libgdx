package com.Juego.zelda;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Ataque  extends  Objeto{
private static final float Duracion=0.6f;
private static final float SIZE=1f;

private float lifeSpan=Duracion;
private final Animation<Texture> animacion;




public Ataque(Vector2 posicion, Vector2 direccion,  Animation<Texture> animacion) {

    super(posicion.x - SIZE/2,
    posicion.y- SIZE/2, SIZE,  SIZE);
    this.animacion=animacion;
}

    @Override
    void update(float deltaTime) {
    this.lifeSpan-=deltaTime;
    }

    public boolean Terminado(){
        return lifeSpan<=0;
    }

    @Override
    public void draw(Batch batch)
    {
     float duracionAnimacion= animacion.getAnimationDuration();
     float PorcentajeAnimacion= 1f-(Math.max(0f,lifeSpan)/Duracion);
     float stateTime=duracionAnimacion*PorcentajeAnimacion;
     texture= animacion.getKeyFrame(stateTime,true);
    super.draw(batch);
    }


}
