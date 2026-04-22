package components;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.MyGdxGame;

import static sun.tools.jconsole.inspector.XDataViewer.dispose;

public class MovingBackground {

    Texture texture ;
    int texture1x, texture2x;
    int speed = 5;
    public void setTexture(Texture texture) {
        dispose();
        this.texture = texture;
    }
    public void MovingBackground(String background){
        texture1x = 0;
        texture2x = MyGdxGame;
        texture = new Texture(background);

    }

    private void dispose() {
        texture.dispose();
    }
}
