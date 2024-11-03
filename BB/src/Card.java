class Card {
    static final int KIND_MAX = 4;  // 카드 무늬의 수
    static final int NUM_MAX = 13;  // 무늬별 카드 수

    static final int SPADE = 4;
    static final int DIAMOND = 3;
    static final int HEART = 2;
    static final int CLOVER = 1;

    int kind;  // 카드 무늬
    int number; // 카드 숫자

    Card() {
        this(SPADE, 1);
    }

    Card(int kind, int number) {
        this.kind = kind;
        this.number = number;
    }

    public String toString() {
        String kindSymbol = getKindSymbol(this.kind);
        String numberSymbol = getNumberSymbol(this.number);
        return kindSymbol + numberSymbol;
    }

    private String getKindSymbol(int kind) {
        switch (kind) {
            case SPADE:
                return "♠︎";
            case DIAMOND:
                return "♦︎";
            case HEART:
                return "♥︎";
            case CLOVER:
                return "♣︎";
            default:
                return "?"; // 알 수 없는 의미
        }
    }

    private String getNumberSymbol(int number) {
        switch (number) {
            case 1: return "A"; // Ace
            case 11: return "J"; // Jack
            case 12: return "Q"; // Queen
            case 13: return "K"; // King
            default: return Integer.toString(number); // 숫자 그대로 사용
        }
    }
}