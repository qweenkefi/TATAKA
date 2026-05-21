package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.screens.GameState;

import java.util.Random;

public class GameSession {
    private int score;
    int destructedStumpsNumber;
    public GameState state;
    long sessionStartTime;
    long pauseStartTime;
    long eventStartTime;
    Random random = new Random();



    public void destructionRegistration() {
        destructedStumpsNumber += 1;
    }
    public void updateScore() {
        score = (int) (TimeUtils.millis() - sessionStartTime) / 100 + destructedStumpsNumber * 100;
    }
    public int getScore() {
        return score;
    }

    public GameSession(){


    }

    public void startGame(){
        state = GameState.PLAYING;
        score = 0;
        destructedStumpsNumber = 0;
        sessionStartTime = TimeUtils.millis();
        eventStartTime = sessionStartTime + 1000L * (random.nextInt(3) + 1);


    }
    public boolean startEventIfNeed() {

        System.out.println("ogo");
        if(eventStartTime < TimeUtils.millis()){
            System.out.println("da");


           // Gdx.app.exit();
        }
        return false;



    }


    public void resumeGame(){
        state = GameState.PLAYING;
        sessionStartTime += TimeUtils.millis() - pauseStartTime;
    }
    public void endGame() {
        updateScore();
        state = GameState.ENDED;


    }
    public void pauseGame(){
        state = GameState.PAUSED;
        pauseStartTime = TimeUtils.millis();

    }


}
