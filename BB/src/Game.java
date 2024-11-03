import java.util.*;

public class Game {
    private static final int MAX_PLAYERS = 4;   // 최대 플레이어 수
    private static final int MIN_PLAYERS = 2;   // 최소 플레이어 수
    private static final int MAX_NICKNAME_LENGTH = 20;  // 최대 닉네임 글자 수
    private static final int TOTAL_GAMES = 100; // 총 게임 수

    private final List<Player> players;

    Game() {
        players = new ArrayList<>();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // 플레이어 수 설정
        int playerCount;
        while (true) {
            System.out.print("플레이어 수를 입력하세요 (최소 " + MIN_PLAYERS + ", 최대 " + MAX_PLAYERS + "명): ");
            playerCount = scanner.nextInt();
            if (playerCount >= MIN_PLAYERS && playerCount <= MAX_PLAYERS) {
                break;
            } else {
                System.out.println("잘못된 플레이어 수입니다. 다시 입력해주세요.");
            }
        }

        // 고유한 닉네임 입력받기
        Set<String> nicknameSet = new HashSet<>(); // 중복 닉네임 체크용
        scanner.nextLine(); // 개행 문자 제거

        for (int i = 0; i < playerCount; i++) {
            String nickname;
            while (true) {
                System.out.print("플레이어 " + (i + 1) + "의 닉네임을 입력하세요 (최대 20자): ");
                nickname = scanner.nextLine();

                if (nickname.length() > MAX_NICKNAME_LENGTH) {
                    System.out.println("닉네임이 너무 깁니다. 20자 이내로 입력해주세요.");
                } else if (nicknameSet.contains(nickname)) {
                    System.out.println("중복된 닉네임입니다. 다른 닉네임을 입력해주세요.");
                } else {
                    nicknameSet.add(nickname);
                    players.add(new Player(nickname));
                    break;
                }
            }
        }

        // 100번의 게임 진행
        for (int gameCount = 1; gameCount <= TOTAL_GAMES; gameCount++) {
            Dealer dealer = new Dealer(); // 새로운 딜러 생성
            System.out.println("=== 게임 " + gameCount + " ===");

            // 플레이어들에게 카드 나눠주기
            for (Player player : players) {
                player.receiveHand(dealer.dealCardsToPlayer());
                player.showHand(); // 각 플레이어의 패 출력
            }

            // 플레이어의 족보 점수 평가
            System.out.println();
            dealer.evaluatePlayersHands(players.toArray(new Player[0]));
            System.out.println();
            dealer.determineWinner(players.toArray(new Player[0])); // 승자 결정
            for (Player player : players) {
                player.showStatus(); // 플레이어 상태 출력
            }
        }

        // 플레이어들의 승리 수를 기준으로 내림차순 정렬
        players.sort((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins())); // 내림차순 정렬

        // 최종 결과 출력
        System.out.println();
        System.out.println("최종 결과:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getWins() + "회 승리");
        }

        scanner.close();
        System.out.println("게임이 종료되었습니다!");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
