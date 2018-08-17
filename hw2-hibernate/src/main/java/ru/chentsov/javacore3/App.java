package ru.chentsov.javacore3;

import ru.chentsov.javacore3.dbService.DBService;
import ru.chentsov.javacore3.dbService.dataset.GoodsDataSet;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main(String[] args)
    {
        DBService dbService = new DBService();

        for (int i = 0; i < 1000; i++) {
            dbService.addGoods(i, "good" + i, (i + 1) * 10);
        }

        System.out.println("Ready to take queries");

        getPriceByTitle(dbService, "good00");
        getPriceByTitle(dbService, "good15");
        dbService.updateGoodsPrice("good15", 123_456);
        getPriceByTitle(dbService, "good15");
        getGoodsByPriceRange(dbService, 200, 250);

        analyzeUserQueries(dbService);
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

    private static void getGoodsByPriceRange(DBService dbService, long floorPrice, long ceilPrice) {
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
