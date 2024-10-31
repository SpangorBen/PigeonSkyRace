package com.PigeonSkyRace.Pigeon.util;

import com.PigeonSkyRace.Pigeon.model.Result;
import com.PigeonSkyRace.Pigeon.repository.PigeonRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import java.awt.Color;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;

public class ExportResults {
    private final List<Result> listResults;
    private static PigeonRepository pigeonRepository;

    public ExportResults(List<Result> listResults) {
        this.listResults = listResults;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("speed", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("distance", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("point", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("arrival date", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("badge", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {
        for (Result result : listResults) {
            table.addCell((result.getSpeed() + "m/min"));
            table.addCell(String.valueOf(result.getDistance()));
            table.addCell(String.valueOf(result.getPoints()));
            table.addCell(formatArrivalDate(result.getArrivalDate()));
            table.addCell(String.valueOf(result.getPigeon().getBadge()));
        }
    }

    public static String formatArrivalDate(LocalDateTime arrivalDate) {
        int hours = arrivalDate.getHour();
        int minutes = arrivalDate.getMinute();
        int seconds = arrivalDate.getSecond();
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    public static String badge(String pigeonId){
        return pigeonRepository.findById(pigeonId).get().getBadge();
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Results", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }
}
