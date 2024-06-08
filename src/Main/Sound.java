package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound(){
        soundURL[0] = getClass().getResource("/Sound/LastTime.wav");
        soundURL[1] = getClass().getResource("/Sound/HitMonster.wav");
        soundURL[2] = getClass().getResource("/Sound/ReceiveDamage.wav");
        soundURL[3] = getClass().getResource("/Sound/FinalBattle.wav");
        soundURL[4] = getClass().getResource("/Sound/GameOver.wav");
        soundURL[5] = getClass().getResource("/Sound/SwingWeapon.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }
}
