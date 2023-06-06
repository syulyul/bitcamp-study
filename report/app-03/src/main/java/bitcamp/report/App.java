package bitcamp.report;

public class App {
    public static void main(String[] args) {
        System.out.println("마트 물품 관리 시스템");
        System.out.println("---------------------------------------------------------------------");

        int no = 100;
        String name = "사과(2KG)";
        int price = 20000;
        boolean limited_Sale = false;
        String type = "식료품";

        System.out.printf("물품 번호: %d\n", no);
        System.out.printf("물품 이름: %s\n", name);
        System.out.printf("물품 가격: %d\n", price);
        System.out.printf("한정 판매: %b\n", limited_Sale);
        System.out.printf("종류(식료품/생활용품/의류/가전제품/리빙): %s\n", type);
    }
}