package com.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.example.ILabTask.labTaskOne;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello from Java Crashtest!");

        computeQrCode("unusual_qr_code", 500, "unusualsquad.com");

        labTaskOne.compute();

        //computeLab2(); TO DO

        //computeLab3(); TO DO

        //computeLab4(); TO DO

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
