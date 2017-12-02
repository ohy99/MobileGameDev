package com.mgd.mgd.Components;

/**
 * Created by 161832Q on 3/12/2017.
 */

public class ScoreSystem implements ComponentBase {

    int CurrentScore;
    int Combo;

    @Override
    public void Init() {
        CurrentScore = Combo = 0;
    }

    @Override
    public void Update(double dt) {

    }

    void AddScore(int DmgDealt) {
        CurrentScore += (DmgDealt * (0.02 * Combo + 1));
    }

    void MinusScore(int DmgReceived) {
        CurrentScore -= (DmgReceived * 0.1);
    }

    void IncreaseCombo() {
        Combo += 1;
    }

    void ResetCombo() {
        Combo = 0;
    }

    int GetCombo() {
        return Combo;
    }

    int GetScore() {
        return CurrentScore;
    }
}
