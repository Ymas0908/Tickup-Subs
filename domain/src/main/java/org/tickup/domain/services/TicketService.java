package org.tickup.domain.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;

import org.tickup.domain.ddd.DomaineService;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

@DomaineService
public class TicketService {

    public byte[] generateTicket(String eventName,
                                 String participantName,
                                 String ticketNumber,
                                 String eventDate,
                                 String location,
                                 String category,
                                 double price) throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);

        // 🎟️ TITRE
        document.add(new Paragraph("TICKET D'ENTRÉE")
                .setBold()
                .setFontSize(22));

        document.add(new Paragraph("\n"));

        document.add(new Paragraph("Événement : " + eventName));
        document.add(new Paragraph("Participant : " + participantName));
        document.add(new Paragraph("Numéro du ticket : " + ticketNumber));
        document.add(new Paragraph("Date : " + eventDate));
        document.add(new Paragraph("Lieu : " + location));
        document.add(new Paragraph("Catégorie : " + category));
        document.add(new Paragraph("Prix payé : " + price + " FCFA"));

        document.add(new Paragraph("\n"));

        // 📌 QR CODE
        Image qrImage = generateQrCode(ticketNumber);
        document.add(qrImage);

        document.add(new Paragraph("\n"));
        document.add(new Paragraph("Présentez ce ticket à l'entrée de l'événement.")
                .setItalic());

        document.close();

        return outputStream.toByteArray();
    }

    public String generateQrCodeForEmail(String ticketNumber) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(ticketNumber, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        // Convertir en base64 pour l'email
        byte[] imageBytes = pngOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    private Image generateQrCode(String text) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        ImageData imageData = ImageDataFactory.create(pngOutputStream.toByteArray());
        return new Image(imageData);
    }
}
