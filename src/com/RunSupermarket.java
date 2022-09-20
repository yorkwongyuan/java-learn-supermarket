package com;
import com.person.Customer;
import com.supermarket.LittleSuperMarket;
import com.supermarket.Products;

import java.util.Scanner;

public class RunSupermarket {
    public static void main(String[] args) {
        LittleSuperMarket littleSuperMarket = new LittleSuperMarket();
        littleSuperMarket.address = "上海大道";
        littleSuperMarket.name = "美宜佳";
        littleSuperMarket.packingCount = 1000000;
        littleSuperMarket.products = new Products[200];
        littleSuperMarket.productSold = new int[littleSuperMarket.products.length];

        Products[] all = littleSuperMarket.products;
        for (int i = 0; i < all.length; i++) {
            Products m = new Products();
            m.id = i + "id";
            m.count = i;
            m.name = "超级大好东西" + i;
            m.purchasePrice = 400;
            m.soldPrice = 200;
            all[i] = m;
        }

        System.out.println("超市开张了");

        boolean open = true;
        Scanner scanner = new Scanner(System.in);
        while (open) {
            System.out.println("本店名字叫做" + littleSuperMarket.name);
            System.out.println("本店地址为" + littleSuperMarket.address);
            System.out.println("一共有停车位" + littleSuperMarket.packingCount);
            System.out.println("今天的营业额为" + littleSuperMarket.incomeSum);
            System.out.println("一共有商品" + littleSuperMarket.products.length + "件");

            Customer customer = new Customer();
            customer.name = "顾客名称" + ((int)(Math.random()*1000));
            customer.money = (1 + Math.random() * 1000);
            customer.isDriving = Math.random() > 0.5;

//            是否是开出来的
//            如果是开车来的
            if(customer.isDriving) {
//                如果此时有车位
                if (littleSuperMarket.packingCount > 0) {
                    littleSuperMarket.packingCount--;
                    System.out.println("欢迎" + customer.name + "的到来，车位编号为" + littleSuperMarket.packingCount);
//                如果此时没有车位
                } else {
                    System.out.println("不好意思，车位已满");
                    continue;
                }
//                走路来的
            } else {
                System.out.println("民众皆成幻影" + customer.name);
            }

            double totalCost = 0;
//            购物逻辑
            while (true) {
                System.out.println("本店提供" + all.length + "种商品，欢迎选购。请输入商品编号");
                int index = scanner.nextInt();
                if (index < 0) {
                    break;
                }
                Products m = all[index];
                System.out.println("你选购的商品名称是" + m.name + ".单价是" + m.soldPrice + ".请问你要买多少个");
                int numToBuy = scanner.nextInt();

                totalCost += numToBuy * m.soldPrice;
//              商品库存减少
                m.count -= numToBuy;
//                销售数量增加
                littleSuperMarket.productSold[index] += numToBuy;
            }
//    购物完成后
            customer.money -= totalCost;

//            开车来的，离开后车位+1
            if (customer.isDriving) {
                littleSuperMarket.packingCount++;
            }

            System.out.println("顾客" + customer.name + "共消费" + totalCost);
//            超市收入
            littleSuperMarket.incomeSum = totalCost;
            System.out.println("还继续营业吗？");
            open = scanner.nextBoolean();
        }
        System.out.println("超市关门了");
        System.out.println("今天的营业额为" + littleSuperMarket.incomeSum + ".");
    }
}
