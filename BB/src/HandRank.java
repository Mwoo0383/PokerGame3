import java.util.*;

class HandRank {
    private Card[] hand; // 플레이어의 카드 5장
    private int rank; // 족보 순위

    public HandRank(Card[] hand) {
        this.hand = hand;
        this.rank = handRanking(); // 족보 평가
    }


    // 족보를 평가하는 메서드
    private int handRanking() {
        if (isRoyalStraightFlush()) return 1;
        if (isBackStraightFlush()) return 2;
        if (isStraightFlush()) return 3;
        if (isPoker()) return 4;
        if (isFullHouse()) return 5;
        if (isFlush()) return 6;
        if (isMountain()) return 7;
        if (isBackStraight()) return 8;
        if (isStraight()) return 9;
        if (isTriple()) return 10;
        if (isTwoPair()) return 11;
        if (isOnePair()) return 12;
        return 13; // 노 페어
    }

    // 족보 평가 메서드들
    private boolean isRoyalStraightFlush() {
        // 로얄 스트레이트 플러시 체크 로직
        return isStraightFlush() && hand[0].number == 10 && hand[1].number == 11 && hand[2].number == 12 && hand[3].number == 13 && hand[4].number == 1; // A
    }

    private boolean isBackStraightFlush() {
        return isStraightFlush() && hand[0].number == 1 && hand[1].number == 2 && hand[2].number == 3 && hand[3].number == 4 && hand[4].number == 5;
    }

    private boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    private boolean isPoker() {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Card card : hand) {
            countMap.put(card.number, countMap.getOrDefault(card.number, 0) + 1);
        }
        return countMap.containsValue(4);
    }

    private boolean isFullHouse() {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Card card : hand) {
            countMap.put(card.number, countMap.getOrDefault(card.number, 0) + 1);
        }
        return countMap.containsValue(3) && countMap.containsValue(2);
    }

    private boolean isFlush() {
        int firstKind = hand[0].kind;
        for (Card card : hand) {
            if (card.kind != firstKind) return false;
        }
        return true;
    }

    private boolean isMountain() {
        int[] numbers = Arrays.stream(hand).mapToInt(card -> card.number).sorted().toArray();
        return Arrays.equals(numbers, new int[]{10, 11, 12, 13, 1});
    }

    private boolean isBackStraight() {
        int[] numbers = Arrays.stream(hand).mapToInt(card -> card.number).sorted().toArray();
        return Arrays.equals(numbers, new int[]{1, 2, 3, 4, 5}); // A, 2, 3, 4, 5
    }

    private boolean isStraight() {
        int[] numbers = Arrays.stream(hand).mapToInt(card -> card.number).sorted().toArray();
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] - numbers[i - 1] != 1) return false;
        }
        return true;
    }

    private boolean isTriple() {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Card card : hand) {
            countMap.put(card.number, countMap.getOrDefault(card.number, 0) + 1);
        }
        return countMap.containsValue(3);
    }

    private boolean isTwoPair() {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Card card : hand) {
            countMap.put(card.number, countMap.getOrDefault(card.number, 0) + 1);
        }
        return countMap.values().stream().filter(count -> count == 2).count() == 2;
    }

    private boolean isOnePair() {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Card card : hand) {
            countMap.put(card.number, countMap.getOrDefault(card.number, 0) + 1);
        }
        return countMap.containsValue(2);
    }
    // 족보의 랭크를 반환한다.
    public int getRank() {
        return rank;
    }
}