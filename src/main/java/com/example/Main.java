package com.example;

import com.example.items.Product;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello from Java Crashtest!");

        computeQrCode("unusual_qr_code", 500, "unusualsquad.com");

        computeLab1();

        //computeLab2(); TO DO

        //computeLab3(); TO DO

        computeLab4();
    }

    public static void computeQrCode(String name, int size, String url) {
        try {
            MultiFormatWriter writer = new MultiFormatWriter();

            BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, size, size);
            drawQr(name, size, bitMatrix);

            System.out.println("QR code exported");
        } catch (WriterException e) {
            System.err.println("No QRcode: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void computeLab1(){
        TaskLabOne task1 = new TaskLabOne();
        List<Product> diabeties = java.util.List.of(
                new Product("Sucre", 6, 60),
                new Product("Chocolat", 18, 5));

        System.out.println(task1.calc(diabeties, 2, true);
    }


    public static void drawQr(String name, int lenght, BitMatrix bitMatrix)  {
        try {
            BufferedImage image = new BufferedImage(lenght, lenght, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < lenght; y++) {
                for (int x = 0; x < lenght; x++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            // Export as PNG
            ImageIO.write(image, "PNG", new File(name));
        } catch (IOException e) {
            System.err.println("Wrong path" + e.getMessage());

        }
    }
}
