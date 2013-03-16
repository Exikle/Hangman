package com.xid_studios.java.game.hangman;

import com.xid_studios.java.game.hangman.gfx.PlayScreenRenderer;
import com.xid_studios.java.game.hangman.gfx.PlayerOneRenderer;
import com.xid_studios.java.game.hangman.gfx.PlayerTwoRenderer;
import com.xid_studios.java.game.hangman.gfx.Renderer;
import com.xid_studios.java.game.hangman.gfx.StartMenuRenderer;

/**
 * All the states in the game.
 */
public enum State {
    START_MENU() {
        @Override
        public Renderer getRenderer() {
            return new StartMenuRenderer();
        }
    },
    PLAYER_ONE_MENU() {
        @Override
        public Renderer getRenderer() {
            return new PlayerOneRenderer();
        }
    },
    PLAYER_TWO_MENU() {
        @Override
        public Renderer getRenderer() {
            return new PlayerTwoRenderer();
        }
    },
    WIN_SCREEN() {
        @Override
        public Renderer getRenderer() {
            return null; // TODO: implement body
        }
    },
    LOSE_SCREEN() {
        @Override
        public Renderer getRenderer() {
            return null; // TODO: implement body
        }
    },
    PLAY_SCREEN() {
        @Override
        public Renderer getRenderer() {
            return new PlayScreenRenderer(); // TODO: implement body
        }
    };

    public abstract Renderer getRenderer();
}
