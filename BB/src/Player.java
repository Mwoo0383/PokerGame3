class Player {
    private final String name; // 플레이어 이름
    Card[] hand; // 플레이어가 가진 카드 배열
    private int seedMoney; // 플레이어의 시드머니
    private int wins; // 승리 수
    private int losses; // 패배 수

    Player(String name) {
        this.name = name;
        this.hand = new Card[5]; // 5장의 카드를 저장할 배열
        this.seedMoney = 10000; // 초기 시드머니
        this.wins = 0; // 초기 승리 수
        this.losses = 0; // 초기 패배 수
    }

    public String getName() {
        return name;
    }

    // Dealer로부터 받은 5장의 카드를 저장하는 메서드
    public void receiveHand(Card[] hand) {
        if (hand.length == 5) {
            this.hand = hand;
        }
    }

    // 플레이어의 패를 출력하는 메서드
    public void showHand() {
        System.out.print(name + "'s hand: ");
        for (Card card : hand) {
            System.out.print(card + " ");
        }
    }
    // 점수와 족보 이름을 반환하는 메서드
    public String getRankInfo() {
        HandRank handRank = new HandRank(this.hand);
        int rankValue = handRank.getRank();
        String rankName;

        switch (rankValue) {
            case 1: rankName = "Royal Straight Flush"; break;
            case 2: rankName = "Back Straight Flush"; break;
            case 3: rankName = "Straight Flush"; break;
            case 4: rankName = "Poker"; break;
            case 5: rankName = "Full House"; break;
            case 6: rankName = "Flush"; break;
            case 7: rankName = "Mountain"; break;
            case 8: rankName = "Back Straight"; break;
            case 9: rankName = "Straight"; break;
            case 10: rankName = "Triple"; break;
            case 11: rankName = "Two Pair"; break;
            case 12: rankName = "One Pair"; break;
            default: rankName = "No Pair"; break;
        }

        return rankValue + " (" + rankName + ")"; // 점수와 족보 이름 반환
    }
    // 게임에서 이겼을 때 호출되는 메서드
    public void winGame() {
        seedMoney += 100; // 시드머니 100원 추가
        wins++; // 승리 수 증가
    }

    public int getWins() {
        return wins;
    }

    // 게임에서 졌을 때 호출되는 메서드
    public void loseGame() {
        losses++; // 패배 수 증가
    }

    // 플레이어의 상태 출력 메서드
    public void showStatus() {
        System.out.println(name + "의 현재 시드머니: " + seedMoney + "원, 승리: " + wins + "회, 패배: " + losses + "회   ");
    }

    // 카드 족보 평가 메서드
    public int evaluateHand() {
        HandRank handRank = new HandRank(hand);
        return handRank.getRank(); // 평가 점수 반환
    }
}