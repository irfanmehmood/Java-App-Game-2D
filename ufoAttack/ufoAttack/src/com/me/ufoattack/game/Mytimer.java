package com.me.ufoattack.game;

import java.util.TimerTask;

import com.badlogic.gdx.utils.Timer;
import com.me.ufoattack.game.Abstracts.ALevel;


public class Mytimer extends Timer.Task  {
    ALevel level;

    public Mytimer(ALevel level) {
        this.level = level;
    }

    @Override
    public void run() {
        this.level.levelDelayedTask();
    }
}
