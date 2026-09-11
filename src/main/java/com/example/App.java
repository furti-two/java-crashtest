package com.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * Simple Java application with ZXing library
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello from Java Crashtest!");
        
        try {
            // Example: Generate a QR code
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode("Hello ZXing!", BarcodeFormat.QR_CODE, 200, 200);
            
            System.out.println("QR Code generated successfully!");
            System.out.println("Dimensions: " + bitMatrix.getWidth() + "x" + bitMatrix.getHeight());
        } catch (WriterException e) {
            System.err.println("Error generating QR code: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
