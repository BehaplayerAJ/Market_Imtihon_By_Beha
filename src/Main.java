import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Market market = new Market();
        Scanner sc = new Scanner(System.in);

        market.marketInterface.addDefaultProducts();
        market.marketInterface.sortProducts();

        while (true) {
            System.out.println("""
                    Добро пожаловать в Todo
                    1. Добавить продукт
                    2. Показать продукты
                    3. Показать купленные продукты
                    4. Удалить продукт
                    5. Продать продукт
                    6. Купить продукт
                    7. Общая сумма продуктов
                    8. Общая сумма купленных продуктов
                    9. Сортировка продуктов
                    10. Выход""");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    market.marketInterface.addProduct();
                    break;
                case 2:
                    market.marketInterface.showProducts();
                    break;
                case 3:
                    market.marketInterface.showUserProducts();
                    break;
                case 4:
                    market.marketInterface.removeProduct();
                    break;
                case 5:
                    market.marketInterface.sellProduct();
                    break;
                case 6:
                    market.marketInterface.buyProduct();
                    break;
                case 7:
                    market.marketInterface.totalProductsPrice();
                    break;
                case 8:
                    market.marketInterface.totalUserProductsPrice();
                    break;
                case 9:
                    market.marketInterface.sortProducts();
                    break;
                case 10:
                    System.out.println("Спасибо, что используете Todo! До свидания!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Такой команды не существует!");
            }
        }
    }
}