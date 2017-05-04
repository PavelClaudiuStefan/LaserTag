package lasertag.data;

import java.io.Serializable;

public class InfoMessage implements Serializable {

    protected static final long serialVersionUID = 1112122200L;

    int sourcePlayer;
    int targetPlayer;

    public InfoMessage() {

    }

    public InfoMessage(int sourcePlayer, int targetPlayer) {
        this.sourcePlayer = sourcePlayer;
        this.targetPlayer = targetPlayer;
    }

    public int getSourcePlayer() {
        return sourcePlayer;
    }

    public int getTargetPlayer() {
        return targetPlayer;
    }
}
