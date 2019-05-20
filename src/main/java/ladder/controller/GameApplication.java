package ladder.controller;

import ladder.domain.LadderGame;
import ladder.domain.LadderGameResult;
import ladder.domain.MatchingResult;
import ladder.domain.reward.RewardGroup;
import ladder.domain.participant.ParticipantGroup;
import ladder.view.InputView;
import ladder.view.OutputView;

public class GameApplication {
    public static void main(String[] args) {
        ParticipantGroup participants = createParticipantGroup();
        RewardGroup rewards = createRewards(participants.getSize());
        LadderGame ladderGame = createLadderGame(participants);

        OutputView.printLadderResult(participants, ladderGame.getLadder(), rewards);

        MatchingResult matchingResult = ladderGame.play();
        LadderGameResult ladderGameResult = matchingResult.map(participants, rewards);
        while (!ladderGameResult.isEnd()) {
            OutputView.printGameResult(ladderGameResult);
        }
    }

    private static ParticipantGroup createParticipantGroup() {
        try {
            return new ParticipantGroup(InputView.inputNames());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
            return createParticipantGroup();
        }
    }

    private static RewardGroup createRewards(final int rewardSize) {
        try {
            return new RewardGroup(InputView.inputRewards(), rewardSize);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
            return createRewards(rewardSize);
        }
    }

    private static LadderGame createLadderGame(final ParticipantGroup participants) {
        try {
            return new LadderGame(participants, InputView.inputLadderHeight());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
            return createLadderGame(participants);
        }
    }
}
