package com.mygdx.game;

import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.screens.GameState;
import managers.MemoryManager;

import java.util.ArrayList;

public class GameSession {
    private int score;
    int destructedMonsterNumber;
    int destructedStumpsNumber;
    public GameState state;
    long nextStumpSpawnTime;
    long sessionStartTime;
    long pauseStartTime;

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
        destructedMonsterNumber = 0;
        destructedStumpsNumber = 0;
        sessionStartTime = TimeUtils.millis();
        nextStumpSpawnTime = sessionStartTime + (long) (GameSettings.STARTING_STUMP_APPEARANCE_COOL_DOWN
                * getStumpPeriodCoolDown());


    }

    public void monsterDestructionRegistration() {
        destructedMonsterNumber += 1;
    }
    public void stumpDestructionRegistration() {
        destructedStumpsNumber += 1;
    }


    public void resumeGame(){
        state = GameState.PLAYING;
        sessionStartTime += TimeUtils.millis() - pauseStartTime;
    }
    public void endGame() {
        updateScore();
        state = GameState.ENDED;
        ArrayList<Integer> recordsTable = MemoryManager.loadRecordsTable();
        if (recordsTable == null) {
            recordsTable = new ArrayList<>();
        }
        int foundIdx = 0;
        for (; foundIdx < recordsTable.size(); foundIdx++) {
            if (recordsTable.get(foundIdx) < getScore()) break;
        }
        recordsTable.add(foundIdx, getScore());
        MemoryManager.saveTableOfRecords(recordsTable);

    }
    public boolean shouldSpawnStump() {
        if (nextStumpSpawnTime <= TimeUtils.millis()) {
            nextStumpSpawnTime = TimeUtils.millis() + (long) (GameSettings.STARTING_STUMP_APPEARANCE_COOL_DOWN
                    * getStumpPeriodCoolDown());
            return true;
        }
        return false;
    }



    private float getStumpPeriodCoolDown() {
        return (float) Math.exp(-0.001 * (TimeUtils.millis() - sessionStartTime + 1) / 1000);
    }
    public void pauseGame(){
        state = GameState.PAUSED;
        pauseStartTime = TimeUtils.millis();

    }


}
