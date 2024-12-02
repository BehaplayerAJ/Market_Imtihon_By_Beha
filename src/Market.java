import java.util.*;

public class Market {
    MarketInterface marketInterface = new MarketInterface() {
        private final ArrayList<String[]> productsList = new ArrayList<>();
        private final ArrayList<String[]> userProductsList = new ArrayList<>();
        private final Scanner sc = new Scanner(System.in);

        @Override
        public void addDefaultProducts() {
            if (productsList.isEmpty()) {
                productsList.add(new String[]{"Яблоко", "Очень вкусное яблоко", "30", UUID.randomUUID().toString()});
                productsList.add(new String[]{"Картошка", "Очень вкусная картошка", "50", UUID.randomUUID().toString()});
                productsList.add(new String[]{"Виноград", "Очень вкусный виноград", "70", UUID.randomUUID().toString()});
            }
        }

        @Override
        public void addProduct() {
            String[] product = createProduct();
            productsList.add(product);
            System.out.println("Продукт успешно добавлен!");
        }

        @Override
        public void removeProduct() {
            if (productsList.isEmpty()) {
                System.out.println("У вас нет продуктов для удаления!");
                return;
            }

            System.out.println("Для удаления продукта введите его ID:");
            String productId = sc.nextLine();

            if (removeProductById(productId)) {
                System.out.println("Продукт был успешно удален!");
            } else {
                System.out.println("Продукт не найден!");
            }
        }

        @Override
        public void sellProduct() {
            if (userProductsList.isEmpty()) {
                System.out.println("У вас нет продуктов для продажи!");
                return;
            }

            System.out.println("Для продажи продукта введите его ID:");
            String productId = sc.nextLine();

            if (sellProductById(productId)) {
                System.out.println("Продукт был успешно продан!");
            } else {
                System.out.println("Продукт не найден!");
            }
        }

        @Override
        public void showProducts() {
            displayProducts(productsList, "У вас нет продуктов для отображения!");
        }

        @Override
        public void showUserProducts() {
            displayProducts(userProductsList, "У вас нет продуктов в корзине для отображения!");
        }

        @Override
        public void buyProduct() {
            if (productsList.isEmpty()) {
                System.out.println("Продуктов для покупки нет!");
                return;
            }

            System.out.println("Для покупки продукта введите его ID:");
            String productId = sc.nextLine();

            if (isProductExist(productId)) {
                System.out.println("Продукт успешно куплен и добавлен в вашу корзину!");
            } else {
                System.out.println("Продукт не найден!");
            }
        }

        @Override
        public void totalProductsPrice() {
            System.out.println("Общая цена всех продуктов: " + calculateTotalPrice(productsList));
        }

        @Override
        public void totalUserProductsPrice() {
            System.out.println("Общая цена ваших продуктов: " + calculateTotalPrice(userProductsList));
        }

        @Override
        public void sortProducts() {
            sortProductList(productsList, "Продукты отсортированы!");
            sortProductList(userProductsList, "Продукты в корзине отсортированы!");
        }

        private String[] createProduct() {
            System.out.println("Введите название продукта (оставьте пустым для значения по умолчанию):");
            String title = sc.nextLine().trim();
            if (title.isEmpty()) title = "Без названия";

            System.out.println("Введите описание продукта (оставьте пустым для значения по умолчанию):");
            String description = sc.nextLine().trim();
            if (description.isEmpty()) description = "Без описания";

            System.out.println("Введите цену продукта (только число, оставьте пустым для значения по умолчанию):");
            String price = sc.nextLine().trim();
            if (price.isEmpty()) {
                price = "10";
            } else {
                try {
                    Integer.parseInt(price);
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод цены. Установлено значение по умолчанию: 10.");
                    price = "10";
                }
            }

            String id = UUID.randomUUID().toString();
            return new String[]{title, description, price, id};
        }

        private boolean removeProductById(String id) {
            return productsList.removeIf(product -> product[3].equals(id));
        }

        private boolean sellProductById(String id) {
            for (int i = 0; i < userProductsList.size(); i++) {
                if (userProductsList.get(i)[3].equals(id)) {
                    productsList.add(userProductsList.remove(i));
                    return true;
                }
            }
            return false;
        }

        private boolean isProductExist(String id) {
            for (int i = 0; i < productsList.size(); i++) {
                if (productsList.get(i)[3].equals(id)) {
                    userProductsList.add(productsList.remove(i));
                    return true;
                }
            }
            return false;
        }

        private void displayProducts(List<String[]> products, String emptyMessage) {
            if (products.isEmpty()) {
                System.out.println(emptyMessage);
                return;
            }

            for (int i = 0; i < products.size(); i++) {
                String[] product = products.get(i);
                System.out.println((i + 1) + ". Название: " + product[0] +
                        ". Описание: " + product[1] +
                        ". Цена: " + product[2] + "$" +
                        ". ID: " + product[3]);
            }
        }

        private String calculateTotalPrice(List<String[]> products) {
            return products.stream()
                    .mapToInt(product -> Integer.parseInt(product[2]))
                    .sum() + "$";
        }

        private void sortProductList(List<String[]> products, String successMessage) {
            if (products.isEmpty()) {
                System.out.println("Нет продуктов для сортировки.");
            } else {
                products.sort(Comparator.comparing(product -> product[0]));
                System.out.println(successMessage);
            }
        }
    };
}
