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

        @Override
        public State previousState() {
            // TODO Auto-generated method stub
            return START_MENU;
        }
    },
    PLAYER_ONE_MENU() {
        @Override
        public Renderer getRenderer() {
            return new PlayerOneRenderer();
        }

        @Override
        public State previousState() {
            // TODO Auto-generated method stub
            return START_MENU;
        }
    },
    PLAYER_TWO_MENU() {
        @Override
        public Renderer getRenderer() {
            return new PlayerTwoRenderer();
        }

        @Override
        public State previousState() {
            // TODO Auto-generated method stub
            return START_MENU;
        }
    },
    WIN_SCREEN() {
        @Override
        public Renderer getRenderer() {
            return null; // TODO: implement body
        }

        @Override
        public State previousState() {
            // TODO Auto-generated method stub
            return START_MENU;
        }
    },
    LOSE_SCREEN() {
        @Override
        public Renderer getRenderer() {
            return null; // TODO: implement body
        }

        @Override
        public State previousState() {
            // TODO Auto-generated method stub
            return START_MENU;
        }
    },
    PLAY_SCREEN() {
        @Override
        public Renderer getRenderer() {
            return new PlayScreenRenderer(); // TODO: implement body
        }

        @Override
        public State previousState() {
            // TODO Auto-generated method stub
            return START_MENU;
        }
    };

    public abstract Renderer getRenderer();
    public abstract State  previousState();
}
