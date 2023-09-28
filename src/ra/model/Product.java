package ra.model;

import java.util.Scanner;

public class Product {
    // Thuộc tính (ATTRIBUTES)
    private int currentIndex = 0;
    private String productId;
    private String productName;
    private float importPrice;
    private float exportPrice;
    private float profit;
    private int quantity;
    private String descriptions;
    private boolean status;

    // Hàm tạo (Constructor)
    public Product() {
    }

    public Product(String productId, String productName, float importPrice, float exportPrice,float profit, int quantity, String descriptions, boolean status) {
        this.productId = productId;
        this.productName = productName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.quantity = quantity;
        this.descriptions = descriptions;
        this.status = status;
        this.profit=profit;

    }

    // Các phương thức (Methods)
    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(float importPrice) {
        this.importPrice = importPrice;
    }

    public float getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(float exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(float profit) {
        this.profit = profit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //    Kiểm tra id có hợp lệ
    private boolean isValidProductId(String productId, Product[] arrProduct) {
        return productId.length() == 4 && productId.charAt(0) == 'P';
    }

    public void inputData(Scanner sc, Product[] arrProduct, int currentIndex) {
        boolean isExistId = true;

        do {
            System.out.println("Nhập vào mã sản phẩm");
            String inputProductId = sc.nextLine();

            if (inputProductId.length() == 4) {
                if (inputProductId.startsWith("P")) {
                    boolean isDuplicate = false;

                    for (int i = 0; i < currentIndex; i++) {
                        if (arrProduct[i] != null && arrProduct[i].getProductId().toLowerCase().equals(inputProductId.toLowerCase())) {
                            isDuplicate = true;
                            break;
                        }
                    }
                    if (isDuplicate) {
                        System.out.println("Mã sản phẩm đã tồn tại. Vui lòng nhập lại.");
                    } else {
                        // Mã sản phẩm hợp lệ và không trùng lặp, thoát khỏi vòng lặp.
                        this.productId = inputProductId;
                        isExistId = false;
                    }
                } else {
                    System.out.println("Kí tự đầu tiên phải bắt đầu bằng chữ P, vui lòng nhập lại.");
                }
            } else {
                System.out.println("Mã sản phẩm phải có đúng 4 kí tự. Vui lòng nhập lại.");
            }
        } while (isExistId);

        boolean isExistName = true;
        do {
            System.out.println("Nhập vào tên sản phẩm");
            String productNameInput = sc.nextLine();
            if (productNameInput.matches("^.{6,50}$")) {

                boolean checkName = false;
                for (int i = 0; i < currentIndex; i++) {

                    if (arrProduct[i] != null && arrProduct[i].getProductName().toLowerCase().equals(productNameInput.toLowerCase())) {
                        checkName = true;
                        break;
                    }
                }
                if (checkName) {
                    System.out.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    this.productName = productNameInput;
                    isExistName = false;
                }
                break;
            } else {
                System.out.println("Nhập tên không hợp lệ vui lòng nhập lại");
            }

        } while (isExistName);

boolean isExistImportPrice=true;
        do {
            System.out.println("Nhập vào giá nhập của sản phẩm");
            try {
                float importPriceInput = Float.parseFloat(sc.nextLine());
                if (importPriceInput <= 0) {
                    System.out.println("Giá nhập phải lớn hơn 0");
                } else {
                    this.importPrice = importPriceInput;
                    isExistImportPrice=false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Giá nhập không hợp lệ. Vui lòng nhập lại.");
            }
        } while (isExistImportPrice);
        boolean isExistExportPrice=true;
        do {
            System.out.println("Nhập vào giá bán của sản phẩm");
            try {
                float exportPriceInput = Float.parseFloat(sc.nextLine());
                if ((this.importPrice + this.importPrice * 0.2) >= exportPriceInput) {
                    System.out.println("Giá bán phải lớn hơn ít nhất 20% so với giá nhập");
                } else {
                    this.exportPrice = exportPriceInput;
                    isExistExportPrice=false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Giá bán không hợp lệ. Vui lòng nhập lại.");
            }
        } while (isExistExportPrice);

        System.out.println("Nhập vào mô tả sản phẩm");
        this.descriptions = sc.nextLine();
        System.out.println("Nhập vào trạng thái sản phẩm");
        this.status = Boolean.parseBoolean(sc.nextLine());
        this.profit=calculateProfit();
    }

    public float calculateProfit() {
        return exportPrice - importPrice;
    }

    public void displayData() {
        System.out.printf("Mã sản phẩm: %s Tên sản phẩm: %s Giá nhập: %.2f Giá xuất: %.2f Số lượng: %d Mô tả: %s Trạng thái: %s\n", this.productId, this.productName, this.importPrice, this.exportPrice, this.quantity, this.descriptions, this.status ? "Còn hàng" : "Hết hàng");
    }

}