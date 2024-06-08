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
        if(gp.gameState == gp.titleState){titleState(code);}
        //play state
        else if(gp.gameState == gp.playState){playState(code);}
        //Pause State
        else if(gp.gameState == gp.pauseState){pauseState(code);gp.playSE(6);}
        //Option State
        else if(gp.gameState == gp.optionsState) {optionState(code);gp.playSE(6);}
        //Option State
        else if(gp.gameState == gp.gameOverState) {gameOverState(code);}
    }

    public void titleState(int code){
        if ((code == KeyEvent.VK_W)) {
            gp.ui.commandNum--;
            gp.playSE(6);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
        }
        if ((code == KeyEvent.VK_S)) {
            gp.ui.commandNum++;
            gp.playSE(6);
            if(gp.ui.commandNum > 1){
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
    public void playState(int code){
        if(code == KeyEvent.VK_W){
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
            gp.playSE(6);
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.playSE(6);
            gp.gameState = gp.optionsState;
        }
    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    public void optionState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNum = 3;
        }
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            gp.playSE(6);
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            gp.playSE(6);
            if (gp.ui.commandNum > maxCommandNum) {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0){
                gp.music.volumeScale--;
                gp.music.checkVolume();
                gp.playSE(7);
            }
            if(gp.ui.commandNum == 1 && gp.se.volumeScale > 0){
                gp.se.volumeScale--;
                gp.playSE(7);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.commandNum == 0 && gp.music.volumeScale < 5){
                gp.music.volumeScale++;
                gp.music.checkVolume();
                gp.playSE(7);
            }
            if(gp.ui.commandNum == 1 && gp.se.volumeScale < 5){
                gp.se.volumeScale++;
                gp.playSE(7);
            }
        }
    }
    public void gameOverState(int code){
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 1;
            }
            gp.playSE(6);
        }
        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1) {
                gp.ui.commandNum = 0;
            }
            gp.playSE(6);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.playSE(7);
                gp.retry();
                gp.playMusic(8);
            } else if (gp.ui.commandNum == 1) {
                gp.gameState = gp.titleState;
                gp.playSE(7);
                gp.playMusic(0);
                gp.restart();
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
