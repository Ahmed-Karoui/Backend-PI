package tn.esprit.util.pdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Annotation;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageLabels;
import com.itextpdf.text.pdf.PdfWriter;

import tn.esprit.entities.Order;
import tn.esprit.entities.OrderDetails;
import tn.esprit.services.OrderServiceImpl;

@Service
public class PDFGenerator {
	
	@Value("${pdfDir}")
	private String pdfDir;
	
	@Value("${reportFileName}")
	private String reportFileName;
	
	@Value("${reportFileNameDateFormat}")
	private String reportFileNameDateFormat;
	
	@Value("${localDateFormat}")
	private String localDateFormat;
	
	@Value("${logoImgPath}")
	private String logoImgPath;
	
	@Value("${logoImgScale}")
	private Float[] logoImgScale;
	
	@Value("${currencySymbol:}")
	private String currencySymbol;
	
	@Value("${table_noOfColumns}")
	private int noOfColumns;
	
	@Value("${table.columnNames}")
	private List<String> columnNames;
	
	@Autowired
	OrderServiceImpl orderServiceImpl;

	private static Font COURIER = new Font(Font.FontFamily.COURIER, 15, Font.BOLD);
	private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
	private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
	
	public byte[] generatePdfReport(List<OrderDetails> orderDetails) {

		Document document = new Document();

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(getPdfNameWithDate(orderDetails.get(0).getOrder().getReference()));
			
			PdfWriter.getInstance(document, fileOutputStream);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			document.open();
			addLogo(document);
			addDocTitle(document,orderDetails.get(0).getOrder());
			createTable(document,noOfColumns,orderDetails);
			//addFooter(document);
			document.close();
			PdfWriter.getInstance(document, stream);
			BufferedOutputStream bo = new BufferedOutputStream(fileOutputStream);
			byte[] pdf = stream.toByteArray();
			
			System.out.println("------------------Your PDF Report is ready!-------------------------" + pdf);
			
			return pdf ;
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}

	private void addLogo(Document document) {
		try {	
			Image img = Image.getInstance(logoImgPath);
			img.scalePercent(logoImgScale[0], logoImgScale[1]);
			img.setAlignment(Element.ALIGN_RIGHT);
			document.add(img);
		} catch (DocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addDocTitle(Document document, Order order) throws DocumentException {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(localDateFormat));
		Paragraph p1 = new Paragraph();
		leaveEmptyLine(p1, 1);
		p1.add(new Paragraph(reportFileName + order.getReference(), COURIER));
		p1.setAlignment(Element.ALIGN_RIGHT);
		leaveEmptyLine(p1, 1);
		
		Paragraph staut = new Paragraph();
		leaveEmptyLine(staut, 1);
		staut.add(new Paragraph("Statut de la commande : " + order.getStatus()));
		staut.add(new Paragraph("Type de paiement : " + order.getTypePaiement()));
		staut.add(new Paragraph("Code postal : " + order.getCodePostal()));
		staut.add(new Paragraph("Adresse : " + order.getAdresse()));
		staut.setAlignment(Element.ALIGN_RIGHT);
		leaveEmptyLine(staut, 1);
		
		//p1.add(new Paragraph("Report generated on " + localDateString, COURIER_SMALL));

		document.add(p1);
		document.add(staut);

	}

	private void createTable(Document document, int noOfColumns,List<OrderDetails> orderDetails) throws DocumentException {
		Paragraph paragraph = new Paragraph();
		leaveEmptyLine(paragraph, 3);
		document.add(paragraph);

		PdfPTable table = new PdfPTable(noOfColumns);
		
		for(int i=0; i<noOfColumns; i++) {
			PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBackgroundColor(BaseColor.CYAN);
			table.addCell(cell);
		}

		table.setHeaderRows(1);
		getDbData(table,orderDetails);
		document.add(table);
	}
	
	private void getDbData(PdfPTable table,List<OrderDetails> orderDetails) {
		
		List<Order> list = this.orderServiceImpl.findAllOrder();
		for (OrderDetails orderDetail : orderDetails) {
			
			table.setWidthPercentage(100);
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			table.addCell(orderDetail.getProduct().getTitle());	
			table.addCell(String.valueOf(orderDetail.getQte()));
			table.addCell(orderDetail.getProduct().getCategory().getName());
			//table.addCell(currencySymbol + order.getStatus());
			
			System.out.println(orderDetail.getProduct().getTitle());
		}
		
	}
	
	private void addFooter(Document document) throws DocumentException {
		Paragraph p2 = new Paragraph();
		leaveEmptyLine(p2, 3);
		p2.setAlignment(Element.ALIGN_MIDDLE);
		p2.add(new Paragraph(
				"------------------------End Of " +reportFileName+"------------------------", 
				COURIER_SMALL_FOOTER));
		
		document.add(p2);
	}

	private static void leaveEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	public String getPdfNameWithDate(String title) {
		String localDateString = LocalDateTime.now().format(DateTimeFormatter.ofPattern(reportFileNameDateFormat));
		File theDir = new File("D:/PdfReportRepo/");
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
		return "D:/PdfReportRepo/Order-Report-"+ title +"-"+localDateString+".pdf";
	}

}
