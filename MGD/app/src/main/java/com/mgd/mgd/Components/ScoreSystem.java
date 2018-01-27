package com.mgd.mgd.Components;

/**
 * Created by 161832Q on 3/12/2017.
 */


public class ScoreSystem implements ComponentBase {

    int CurrentScore = 0;
    int Combo = 0;

    @Override
    public void Init() {
        CurrentScore = Combo = 0;
    }

    @Override
    public void Update(double dt) {

    }

    public void AddScore(int DmgDealt) {
        CurrentScore += (DmgDealt * (0.02 * Combo + 1));
    }

    public void MinusScore(int DmgReceived) {
        CurrentScore -= (DmgReceived * 0.1);
    }

    public void IncreaseCombo() {
        Combo += 1;
    }

    public void ResetCombo() {
        Combo = 0;
    }

    public int GetCombo() {
        return Combo;
    }

    public int GetScore() {
        return CurrentScore;
    }
}
