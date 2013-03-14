package com.xid_studios.java.game.hangman.buffer;

import java.awt.image.BufferStrategy;

import com.xid_studios.java.game.hangman.State;

public abstract class AbstractWindowBufferStrategy {
    public AbstractWindowBufferStrategy(State currentState, BufferStrategy bs) {
        switch (currentState) {
        case START_MENU:
            new StartMenuBufferStrategy(bs);
            // case PLAYER_ONE_MENU:
            // return new PlayerOneMenuBufferStrategy(bs);
            // case PLAYER_TWO_MENU:
            // return new PlayerTwoMenuBufferStrategy(bs);
            // case WIN_SCREEN:
            // return new WinScreenBufferStrategy(bs);
            // case LOSE_SCREEN:
            // return new LoseScreenBufferStrategy(bs);
        case PLAY_SCREEN:
            new PlayScreenBufferStrategy(bs);
        }
    }

    abstract void render();
}
