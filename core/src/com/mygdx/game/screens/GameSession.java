package com.mygdx.game.screens;

import com.badlogic.gdx.utils.TimeUtils;
import managers.MemoryManager;

import java.util.ArrayList;

public class GameSession {
    private int score;
    int destructedStumpsNumber;
    public GameState state;
    long nextTrashSpawnTime;
    long sessionStartTime;
    long pauseStartTime;

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
    public void pauseGame(){
        state = GameState.PAUSED;
        pauseStartTime = TimeUtils.millis();

    }


}
