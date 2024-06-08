package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed,enterPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    @Override
    public void keyTyped(KeyEvent e) {    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        //title state
        if(gp.gameState == gp.titleState){
            if ((code == KeyEvent.VK_W)) {
                gp.ui.commandNum--;
                gp.playSE(6);
                if(gp.ui.commandNum<0){
                    gp.ui.commandNum = 2;
                }
            }
            if ((code == KeyEvent.VK_S)) {
                gp.ui.commandNum++;
                gp.playSE(6);
                if(gp.ui.commandNum>2){
                    gp.ui.commandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.commandNum == 0){
                    gp.playSE(7);
                    gp.gameState = gp.playState;
                    gp.stopMusic();
                    gp.playMusic(8);
                }
                if (gp.ui.commandNum == 1) {
                    gp.playSE(7);
                    System.exit(0);
                }
            }  
        }
        //play state
        else if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.pauseState;
            }
            else {
                if( gp.gameState == gp.pauseState){
                    gp.gameState =gp.playState;
                }
            }
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;           
        }
        //Option State
        else if(code == KeyEvent.VK_ESCAPE){
            if(gp.gameState == gp.playState){
                gp.gameState = gp.optionsState;
            }
            else {
                if( gp.gameState == gp.optionsState){
                    gp.gameState =gp.playState;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}
