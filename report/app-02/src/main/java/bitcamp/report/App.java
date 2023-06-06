package bitcamp.report;

public class App {
    public static void main(String[] args) {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");

        System.out.print("물품 번호: ");
        System.out.println(100);

        System.out.printf("물품 이름: %s", "사과(2KG)");
        System.out.println();

        System.out.println("물품 가격: " + 20000);

        System.out.printf("한정 판매: %b\n", false);

        System.out.printf("종류(식료품/생활용품/의류/가전제품/리빙): %s\n", "식료품");
    }
}
