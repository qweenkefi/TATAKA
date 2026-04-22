package components;

import com.badlogic.gdx.graphics.Texture;

import static sun.tools.jconsole.inspector.XDataViewer.dispose;

public class MovingBackground {

    Texture texture ;
    public void setTexture(Texture texture) {
        dispose();
        this.texture = texture;
    }

    private void dispose() {
        texture.dispose();
    }
}
