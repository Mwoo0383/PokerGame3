import java.util.*;

class Deck {
    final int CARD_NUM = 52; // 카드의 개수
    List<Card> cards; // 카드 리스트로 변경

    // Deck의 카드를 초기화한다.
    Deck() {
        cards = new ArrayList<>(CARD_NUM);
        for (int k = Card.KIND_MAX; k > 0; k--) {
            for (int n = 1; n <= Card.NUM_MAX; n++) {
                cards.add(new Card(k, n)); // 카드 추가
            }
        }
    }

    // 지정된 위치(index)에 있는 카드 하나를 선택하고 해당 카드를 제거한다.
    Card pick(int index) {
        return cards.remove(index); // 카드 선택 후 제거
    }

    // Deck에서 임의의 위치에 있는 카드 하나를 선택하고 해당 카드를 제거한다.
    Card pick() {
        int index = (int)(Math.random() * cards.size());
        return pick(index); // 카드 선택 후 제거
    }

    // 카드의 순서를 섞는다.
    void shuffle() {
        Collections.shuffle(cards); // 리스트를 섞음
    }
}
