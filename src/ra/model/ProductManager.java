package ra.model;

import java.util.Scanner;

public class ProductManager {

    static Product[] products = new Product[100];
    static int count=0;
    static String productId;
    static String productName;
    static float importPrice;
    static float exportPrice;
    static int quantity;
    static String descriptions;
    static boolean status;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("*********************MENU********************");
            System.out.println("1.Nhập thông tin n sản phẩm");
            System.out.println("2.Hiển thị thông tin sản phẩm");
            System.out.println("3.Tính lợi nhuận các sản phẩm");
            System.out.println("4.sắp xếp các sản phẩm theo lợi nhuận giảm dần");
            System.out.println("5.Thống kê sản phẩm theo giá");
            System.out.println("6.Tìm theo tên sản phẩm");
            System.out.println("7.Nhập sản phẩm");
            System.out.println("8.Bán sản phẩm");
            System.out.println("9.Thoát");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                   addProduct(sc);
                    break;
                case 2:
                    showProduct();
                    break;
                case 3:
                    calProfit(sc);
                    break;
                case 4:
                  displaySortedProductsByDecendingProfit(products,count);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Vui lòng nhập từ 1-9");
            }
        } while (true);
    }

    public static void showProduct() {
        for (Product product : products
        ) {
            if (product != null) {
                product.displayData();
            } else {
                break;
            }
        }
    }

    public static void addProduct(Scanner sc) {

        System.out.println("Nhập số lượng sản phẩm muốn thêm");
        int n =Integer.parseInt(sc.nextLine()) ;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                Product product = new Product();
                product.inputData(sc,products,i);
                products[i]=product;
                count++;
            }
        } else {
            System.out.println("Số lượng sản phẩm phải lớn hơn 0");
        }
    }

    // tìm ra vị trí
public static int getIndexProductById(String productId){
    for (int i = 0; i < count; i++) {
        if (products[i].getProductId().equals(productId)){
            return i;
        }
    }
    return -1;
}
// tính lợi nhuận
    public static void calProfit(Scanner sc){
        System.out.println("Nhập vào mã sản phẩm để in ra lợi nhuận");
        String productId=sc.nextLine();
        int indexProduct=getIndexProductById(productId);
        if (indexProduct>=0){
           // in ra lợi nhuận
Product product =products[indexProduct];
            System.out.printf("Mã số sản phẩm: %s Lợi nhuận: %.2f\n",product.getProductId(),product.calculateProfit());
        }else {
            System.out.println("Sản phẩm không có trong danh sách");
        }

    }


    public static void displaySortedProductsByDecendingProfit(Product[] arrProducts, int currentIndex) {
        // Sử dụng thuật toán sắp xếp chèn (Insertion Sort) để sắp xếp mảng theo lợi nhuận giảm dần.
        for (int i = 1; i < currentIndex; i++) {
            Product key = arrProducts[i];
            int j = i - 1;
            while (j >= 0 && arrProducts[j].getProfit() < key.getProfit()) {
                arrProducts[j + 1] = arrProducts[j];
                j = j - 1;
            }
            arrProducts[j + 1] = key;
        }

        // Hiển thị danh sách sản phẩm đã sắp xếp.
        for (int i = 0; i < currentIndex; i++) {
            arrProducts[i].displayData();
            System.out.println("Lợi nhuận: " + arrProducts[i].getProfit());
        }
    }

}
