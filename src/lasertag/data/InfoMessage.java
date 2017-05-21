package lasertag.data;

import java.io.Serializable;

public class InfoMessage implements Serializable {

    protected static final long serialVersionUID = 1112122200L;

    private int sourcePlayer;
    private int targetPlayer;

    private boolean endConnection;

    public InfoMessage(String end) {
        if(end.compareTo("end") == 0) {
            endConnection = true;
        }
    }

    public InfoMessage(int sourcePlayer, int targetPlayer) {
        this.sourcePlayer = sourcePlayer;
        this.targetPlayer = targetPlayer;
        endConnection = false;
    }

    public int getSourcePlayer() {
        return sourcePlayer;
    }

    public int getTargetPlayer() {
        return targetPlayer;
    }

    public boolean isEnding(){
        return endConnection;
    }
}
