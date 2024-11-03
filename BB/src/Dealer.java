import java.util.Arrays;

class Dealer {
    private Deck deck;

    Dealer() {
        deck = new Deck(); // 새로운 덱 생성
        deck.shuffle(); // 카드 섞기
    }

    // Player에게 5장의 카드를 나눠주는 메서드
    public Card[] dealCardsToPlayer() {
        Card[] hand = new Card[5];
        for (int i = 0; i < 5; i++) {
            hand[i] = deck.pick(); // 덱에서 카드를 뽑아 손에 추가
        }
        return hand; // 5장의 카드 배열 반환
    }

    // 플레이어의 족보 점수를 비교, 평가하는 메서드
    public void evaluatePlayersHands(Player[] players) {
        for (Player player : players) {
            if (player != null) {
                String rankInfo = player.getRankInfo(); // 족보 점수와 이름 가져오기
                System.out.print(player.getName() + "의 족보 점수: " + rankInfo + "    ");
            }
        }
    }
    public void determineWinner(Player[] players) {
        Player winner = null;
        int highestScore = Integer.MAX_VALUE; // 높은 점수일수록 좋으므로 초기 값을 최대값으로 설정
        int highestCard = 0; // 가장 높은 카드 초기화

        for (Player player : players) {
            // 현재 플레이어의 족보 점수 평가
            int currentScore = player.evaluateHand();

            // 현재 플레이어의 카드 중 가장 높은 카드 찾기
            int highestCardInHand = 0; // 현재 플레이어의 손에서 가장 높은 카드
            for (Card card : player.hand) {
                if (card.number > highestCardInHand) {
                    highestCardInHand = card.number; // 가장 높은 카드로 업데이트
                }
            }

            // 승리 조건 확인
            boolean isNewWinner = false; // 새로운 승리자 여부
            if (currentScore < highestScore) {
                isNewWinner = true; // 점수가 낮아서 새로운 승리자로 설정
            } else if (currentScore == highestScore) {
                if (highestCardInHand > highestCard) {
                    isNewWinner = true; // 족보 점수가 같고 카드 숫자가 더 높아서 새로운 승리자로 설정
                }
            }

            // 새로운 승리자일 경우 업데이트
            if (isNewWinner) {
                highestScore = currentScore;
                highestCard = highestCardInHand;
                winner = player; // 현재 플레이어를 승리자로 설정
            }
        }


        if (winner != null) {
            winner.winGame(); // 승리한 플레이어에게 보상
            for (Player player : players) {
                if (player != winner) {
                    player.loseGame(); // 나머지 플레이어는 패배 처리
                }
            }
            System.out.println("승리한 플레이어: " + winner.getName());
        }
    }
}