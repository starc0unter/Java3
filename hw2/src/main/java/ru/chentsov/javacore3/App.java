package ru.chentsov.javacore3;

/*
Сформировать таблицу товаров (id, prodid, title, cost) запросом из Java-приложения:
+ id – порядковый номер записи, первичный ключ;
+ prodid – уникальный номер товара;
+ title – название товара;
+ cost – стоимость.
2. При запуске приложения очистить таблицу и заполнить 10000 товаров вида:
+ id_товара 1 товар1 10
+ id_товара 2 товар2 20
+ id_товара 3 товар3 30
+ …
+ id_товара 10000 товар10000 100000
3. Написать консольное приложение, которое позволяет узнать цену товара по его имени, либо
вывести сообщение «Такого товара нет», если товар не обнаружен в базе. Консольная
команда: «/цена товар545».
4. Добавить возможность изменения цены товара. Указываем имя товара и новую цену.
Консольная команда: «/сменитьцену товар10 10000».
5. Вывести товары в заданном ценовом диапазоне. Консольная команда: «/товарыпоцене 100
600».
 */

import ru.chentsov.javacore3.dbService.DBService;
import ru.chentsov.javacore3.dbService.dataset.GoodsDataSet;

import java.util.List;
import java.util.Scanner;

public class App
{

    public static void main(String[] args)
    {
        DBService dbService = new DBService();

        //measuring time for consequent SQL queries
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            dbService.addGoods(i, "good" + i, (i + 1) * 10);
        }
        long end  = System.currentTimeMillis();
        System.out.println("No batch: " + (end - start));

        dbService.cleanDB();
        //measuring time for batch SQL query
        long startBatch = System.currentTimeMillis();
        dbService.addGoodsByBatch(10_000);
        long endBatch = System.currentTimeMillis();
        System.out.println("Batch: " + (endBatch - startBatch));

        analyzeUserQueries(dbService);

        dbService.cleanDB();
    }

    private static void getPriceByTitle(DBService dbService, String title) {
        List<GoodsDataSet> rows = dbService.get(title);
        if(rows.isEmpty()) System.out.println("No such good exists");
        else {
            for(GoodsDataSet data : rows) {
                System.out.println("Price for " + data.getTitle() + " is " + data.getPrice());
            }
        }
    }

    @SuppressWarnings("all")
    private static void getGoodsByPriceRange(DBService dbService, long floorPrice, long ceilPrice) {
        System.out.println("Printing goods with prices in range: [" + floorPrice + ", " + ceilPrice + "]:");
        List<GoodsDataSet> rows = dbService.get(floorPrice, ceilPrice);
        if(rows.isEmpty()) System.out.println("No such good exists");
        else {
            for(GoodsDataSet data : rows) {
                System.out.println(data);
            }
        }
    }

    private static void analyzeUserQueries(DBService dbService) {
        final String updatePriceCommand = "/updatePrice";
        final String getPriceCommand = "/getPrice";
        final String getGoodsInPriceRangeCommand = "/getGoodsInRange";

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.next();
            switch (command) {
                case updatePriceCommand:
                    dbService.updateGoodsPrice(scanner.next(), scanner.nextLong());
                    break;
                case getPriceCommand:
                    getPriceByTitle(dbService, scanner.next());
                    break;
                case getGoodsInPriceRangeCommand:
                    getGoodsByPriceRange(dbService, scanner.nextLong(), scanner.nextLong());
                    break;
                default:
                    return;
            }
        }
    }

}
