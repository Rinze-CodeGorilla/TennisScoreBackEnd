package com.example.demo;

public class Game {
    public PlayerScore p1 = new PlayerScore();
    public PlayerScore p2 = new PlayerScore();

    public PlayerScore getP1() {
        return p1;
    }

    public PlayerScore getP2() {
        return p2;
    }

    public void score1() {
        score(p1, p2);
    }

    public void score2() {
        score(p2, p1);
    }

    void score(PlayerScore a, PlayerScore b) {
        a.game++;
        // 0  1  2  3  4
        // 0 15 30 40 adv
        // 40 - 60 = adv
        // 40 - 40 = deuce

        if (a.game >= 4 && a.game - b.game >= 2) {
            a.game = 0;
            b.game = 0;
            a.set++;
        }
        if (a.set >= 6 && a.set - b.set >= 2) {
            a.set = 0;
            b.set = 0;
            a.set++;
        }
    }
}
