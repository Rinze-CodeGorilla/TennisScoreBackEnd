package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
@ResponseBody
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class Controller {

    Game game;

    Controller(Game game) {
        this.game = game;
    }

    @GetMapping("/score")
    Score getScore(HttpSession session) {
        return new Score(game);

    }

    @PostMapping("/score")
    Score incrementScore(@RequestBody ScoreRequest request) {
        if (request.player().equals("Player 1")) {
            game.score1();
        } else {
            game.score2();
        }
        return new Score(game);
    }


    private record ScoreRequest(String player) {
    }

    public record Score(String match, String set, String game, String tiebreak) {
        Score(Game game) {
            this(
                    set(game.getP1().match, game.getP2().match),
                    set(game.getP1().set, game.getP2().set),
                    game(game.getP1().game, game.getP2().game),
                    null
            );
        }

        private static String game(int a, int b) {
            if (a == b) return switch (a) {
                case 0 -> "love-all";
                case 1 -> "15-all";
                case 2 -> "30-all";
                default -> "deuce";
            };
            if (a >= 3 && b >= 3) return a > b ? "Advantage player 1" : "Advantage player 2";
            return gameScoreToWord(a) + "-" + gameScoreToWord(b);
        }

        private static String set(int a, int b) {
            return a + "-" + b;
        }

        private static String gameScoreToWord(int score) {
            return switch (score) {
                case 0 -> "love";
                case 1 -> "15";
                case 2 -> "30";
                case 3 -> "40";
                default -> throw new IllegalStateException("Unexpected value: " + score);
            };
        }
    }
}
