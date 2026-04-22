package components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.GameSettings;
import com.mygdx.game.MyGdxGame;


import static sun.tools.jconsole.inspector.XDataViewer.dispose;

public class MovingBackground {
    Texture texture ;
    int texture1x, texture2x;
    int speed = 5;

    public MovingBackground(String background){
        texture1x = 0;
        texture2x = GameSettings.SRC_WIDTH;
        texture = new Texture(background);

    }

    public void draw(Batch batch) {
        batch.draw(texture, texture1x, 0, GameSettings.SRC_WIDTH, GameSettings.SRC_HEIGHT);
        batch.draw(texture, texture2x, 0, GameSettings.SRC_WIDTH, GameSettings.SRC_HEIGHT);

    }

    public void move(){
        texture1x -= speed;
        texture2x -= speed;

        if (texture1x <= -GameSettings.SRC_WIDTH){
                texture1x = GameSettings.SRC_WIDTH;
        }
        if (texture2x <= -GameSettings.SRC_WIDTH){
            texture2x = GameSettings.SRC_WIDTH;
        }
    }



    public void setTexture(Texture texture) {
        dispose();
        this.texture = texture;
    }

    private void dispose() {
        texture.dispose();
    }
}
