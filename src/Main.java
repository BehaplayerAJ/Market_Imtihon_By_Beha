import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Market market = new Market();
        Scanner sc = new Scanner(System.in);

        market.marketInterface.addDefaultProducts();
        market.marketInterface.sortProducts();

        while (true) {
            System.out.println("Добро пожаловать в Todo\n" + "1. Добавить продукт\n" + "2. Показать продукты\n" + "3. Показать купленные продукты\n" + "4. Удалить продукт\n" + "5. Продать продукт\n" + "6. Купить продукт\n" + "7. Общая сумма продуктов\n" + "8. Общая сумма купленных продуктов\n" + "9. Сортировка продуктов\n" + "10. Выход");
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