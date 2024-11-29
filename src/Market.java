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
            System.out.println("Ваша задача успешно добавлена!");
        }

        @Override
        public void removeProduct() {
            if (!productsList.isEmpty()) {
                System.out.println("Для удаления продукта введите его ID: ");
                String UTId = sc.nextLine();

                if (removeProductById(UTId)) {
                    System.out.println("Ваш продукт был удален!");
                } else {
                    System.out.println("Продукт не найден!");
                }
            } else {
                System.out.println("У вас нет продуктов для удаления!");
            }
        }

        @Override
        public void sellProduct() {
            if (!userProductsList.isEmpty()) {
                System.out.println("Для того чтобы продать продукт введите его ID: ");
                String UTId = sc.nextLine();

                if (sellProductById(UTId)) {
                    System.out.println("Ваш продукт был продан!");
                } else {
                    System.out.println("продукт не найден!");
                }
            } else {
                System.out.println("У вас нет продуктов для продавания!");
            }
        }

        @Override
        public void showProducts() {
            if (!productsList.isEmpty()) {
                for (int i = 0; i < productsList.size(); i++) {
                    String[] product = productsList.get(i);
                    System.out.println((i + 1) + ". Название: " + product[0] + ". Описание: " + product[1] + ". Цена: " + product[2] + "$" + ". ID: " + product[3] + ".");
                }
            } else {
                System.out.println("У вас нет продуктов для отображения!");
            }
        }

        @Override
        public void showUserProducts() {
            if (!userProductsList.isEmpty()) {
                for (int i = 0; i < userProductsList.size(); i++) {
                    String[] product = userProductsList.get(i);
                    System.out.println((i + 1) + ". Название: " + product[0] + ". Описание: " + product[1] + ". Цена: " + product[2] + "$" + ". ID: " + product[3] + ".");
                }
            } else {
                System.out.println("У вас нет продуктов для отображения!");
            }
        }

        @Override
        public void buyProduct() {
            if (!productsList.isEmpty()) {
                System.out.println("Для покупки продукта введите его ID: ");
                String UTId = sc.nextLine();

                if (isProductExist(UTId)) {
                    System.out.println("Продукт был успешно куплен и добавлен на вашу корзину!");
                } else {
                    System.out.println("Продукт не найден!");
                }
            } else {
                System.out.println("Продукт не найден!");
            }
        }

        @Override
        public void totalProductsPrice() {
            if (!productsList.isEmpty()) {
                System.out.println(totalPP());
            } else {
                System.out.println("У вас нет продуктов для отображения!");
            }
        }

        @Override
        public void totalUserProductsPrice() {
            if (!productsList.isEmpty()) {
                System.out.println(totalUPP());
            } else {
                System.out.println("У вас нет продуктов для отображения!");
            }
        }

        private String totalPP() {
            int totalPrice = 0;
            for (String[] strings : productsList) {
                totalPrice += Integer.parseInt(strings[2]);
            }

            return totalPrice + "$";
        }

        private String totalUPP() {
            int totalPrice = 0;
            for (String[] strings : userProductsList) {
                totalPrice += Integer.parseInt(strings[2]);
            }

            return totalPrice + "$";
        }

        @Override
        public void sortProducts() {
            if (!productsList.isEmpty()) {
                productsList.sort(Comparator.comparing(product -> product[0]));
                System.out.println("Продукты отсортированы!");
            } else {
                System.out.println("У вас нет продуктов для сортировки!");
            }

            if (!userProductsList.isEmpty()) {
                userProductsList.sort(Comparator.comparing(product -> product[0]));
                System.out.println("Продукты отсортированы!");
            } else {
                System.out.println("У вас нет продуктов в корзине для сортировки!");
            }


        }

        private String[] createProduct() {
            System.out.println("Введите название продукта (оставьте пустым, чтобы оставить текущий):");
            String UPTitle = sc.nextLine();
            if (UPTitle.isBlank()) {
                UPTitle = null != null ? null : "Без названия";
            }

            System.out.println("Введите описание продукта (оставьте пустым, чтобы оставить текущее):");
            String UPDesc = sc.nextLine();
            if (UPDesc.isBlank()) {
                UPDesc = null != null ? null : "Без описания";
            }

            System.out.println("Введите цену продукта (оставьте пустым, чтобы оставить текущее):");
            String UPPrice = sc.nextLine();
            if (UPPrice.isBlank()) {
                UPPrice = null != null ? null : "10";
            }

            String id = null != null ? null : UUID.randomUUID().toString();

            return new String[]{UPTitle, UPDesc, UPPrice, id};
        }

        private boolean removeProductById(String id) {
            return productsList.removeIf(product -> product[3].equals(id));
        }

        private boolean sellProductById(String id) {
            for (int i = 0; i < userProductsList.size(); i++) {
                if (Objects.equals(userProductsList.get(i)[3], id)) {
                    productsList.add(userProductsList.get(i));
                    userProductsList.remove(i);
                    return true;
                }
            }
            return false;
        }

        private boolean isProductExist(String id) {
            for (int i = 0; i < productsList.size(); i++) {
                if (Objects.equals(productsList.get(i)[3], id)) {
                    userProductsList.add(productsList.get(i));
                    productsList.remove(i);
                    return true;
                }
            }
            return false;
        }
    };
}