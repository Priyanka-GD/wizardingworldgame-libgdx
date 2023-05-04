package com.gameclasses.controller.observer;


import com.gameclasses.view.gamescreens.BackgroundScreen;

public abstract class ObserverCheating {
    protected BackgroundScreen subject;

    public abstract void updateCheating ();
}
