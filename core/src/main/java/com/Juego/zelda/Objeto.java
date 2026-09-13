package com.Juego.zelda;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Objeto {
protected static final Vector2 TMP_VEC2=new Vector2();

protected final Rectangle rectangle;
protected  Texture texture;

public Objeto(float x, float y, float w, float h, Texture texture)
{
	this.rectangle=new Rectangle(x,y,w,h);
	this.texture =texture;

}
    public Vector2 getCenter(Vector2 centro) {
        return rectangle.getCenter(centro);
    }

public Objeto(float x, float y, float w, float h)
{
	this(x,y,w,h,null);

}
public boolean overlaps(Objeto otro)
{
return rectangle.overlaps(otro.rectangle);
}

public void draw(Batch batch)
{
if(texture==null)
{
	return;
}
batch.draw(texture,rectangle.x,rectangle.y,rectangle.width,rectangle.height);
}

    public void setTexture(Texture texture) {
        this.texture = texture;
    }
    public Texture getTexture(Texture texture) {
        this.texture = texture;
        return texture;
    }

abstract void update(float deltaTime);
}
