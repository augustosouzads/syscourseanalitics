package com.sys.course.analitics.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sys.course.analitics.models.Aula;

public class PDFUtils {
	
    	public static ByteArrayInputStream gerarPdf(List<Aula> list) {

    		final BaseColor BLUE = new BaseColor(0, 200, 255);
    		final BaseColor GRAY = new BaseColor(128, 128, 128);
    		final BaseColor LIGHTGRAY = new BaseColor(228, 228, 228);
    		final BaseColor WHITE = new BaseColor(255, 255, 255);

    	Document doc = new Document();		
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		
		try {
			PdfPTable table = new PdfPTable(7);
			table.setWidthPercentage(102);
			table.setWidths(new int[] {4,4,12,8,6,7,7});
						
			PdfPCell header;
			
			header = new PdfPCell(new Phrase("Turma"));
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBackgroundColor( BLUE );
			header.setBorder(1);
			header.setBorderWidthTop(2);
			header.setBorderWidthBottom(1);
			header.setBorderWidthLeft(2);
			header.setBorderColor(GRAY);
			header.setBorderColorBottom(BLUE);
			table.addCell(header);
			
			header = new PdfPCell(new Phrase("Curso"));
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBackgroundColor( BLUE );
			header.setBorder(1);
			header.setBorderWidthTop(2);
			header.setBorderWidthBottom(1);
			header.setBorderColor(GRAY);
			header.setBorderColorBottom(BLUE);
			table.addCell(header);
			
			header = new PdfPCell(new Phrase("Disciplina"));
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBackgroundColor( BLUE );
			header.setBorder(1);
			header.setBorderWidthTop(2);
			header.setBorderWidthBottom(1);
			header.setBorderColor(GRAY);
			header.setBorderColorBottom(BLUE);
			table.addCell(header);
			
			header = new PdfPCell(new Phrase("Dia Da Semana"));
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBackgroundColor( BLUE );
			header.setBorder(1);
			header.setBorderWidthTop(2);
			header.setBorderWidthBottom(1);
			header.setBorderColor(GRAY);
			header.setBorderColorBottom(BLUE);
			table.addCell(header);
			
			header = new PdfPCell(new Phrase("Data"));
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBackgroundColor( BLUE );
			header.setBorder(1);
			header.setBorderWidthTop(2);
			header.setBorderWidthBottom(1);
			header.setBorderColor(GRAY);
			header.setBorderColorBottom(BLUE);
			table.addCell(header);
			
			header = new PdfPCell(new Phrase("Matriculados"));
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBackgroundColor( BLUE );
			header.setBorder(1);
			header.setBorderWidthTop(2);
			header.setBorderWidthBottom(1);
			header.setBorderColor(GRAY);
			header.setBorderColorBottom(BLUE);
			table.addCell(header);
			
			header = new PdfPCell(new Phrase("Conectados"));
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			header.setBackgroundColor( BLUE );
			header.setBorder(1);
			header.setBorderWidthTop(2);
			header.setBorderWidthBottom(1);
			header.setBorderWidthRight(2);
			header.setBorderColor(GRAY);
			header.setBorderColorBottom(BLUE);
			table.addCell(header);
			
			for (Aula data: list) {
				PdfPCell body;
				
				body = new PdfPCell(new Phrase(data.getDisciplinaId().getTurmaId().getTurmaNome()));
				body.setHorizontalAlignment(Element.ALIGN_CENTER);
				body.setBorderColor(LIGHTGRAY);
				body.setBackgroundColor(LIGHTGRAY);
				body.setBorderWidthLeft(2);
				body.setBorderColorLeft(GRAY);
				table.addCell(body);
				
				body = new PdfPCell(new Phrase(data.getDisciplinaId().getTurmaId().getCursoId().getSigla()));
				body.setHorizontalAlignment(Element.ALIGN_CENTER);
				body.setBorderColor(WHITE);
				
				table.addCell(body);
				
				body = new PdfPCell(new Phrase(data.getDisciplinaId().getTitulo()));
				body.setHorizontalAlignment(Element.ALIGN_CENTER);
				body.setBorder(1);
				body.setBorderColor(LIGHTGRAY);
				body.setBackgroundColor(LIGHTGRAY);
				table.addCell(body);
				
				body = new PdfPCell(new Phrase(data.getDisciplinaId().getDiaDaSemana()));
				body.setHorizontalAlignment(Element.ALIGN_CENTER);
				body.setBorderColor(WHITE);
				table.addCell(body);
				
				body = new PdfPCell(new Phrase(data.getData().format(DateTimeFormatter.ofPattern("dd/LL/yyyy"))));
				body.setHorizontalAlignment(Element.ALIGN_CENTER);
				body.setBorderColor(LIGHTGRAY);
				body.setBackgroundColor(LIGHTGRAY);
				table.addCell(body);
				
				body = new PdfPCell(new Phrase(" "+data.getDisciplinaId().getQuantidadeAluno()));
				body.setHorizontalAlignment(Element.ALIGN_CENTER);
				body.setBorderColor(WHITE);
				table.addCell(body);
				
				body = new PdfPCell(new Phrase(" "+data.getQuantidadeAlunos()));
				body.setHorizontalAlignment(Element.ALIGN_CENTER);
				body.setBorderColor(LIGHTGRAY);
				body.setBackgroundColor(LIGHTGRAY);
				body.setBorderWidthRight(2);
				body.setBorderColorRight(GRAY);
				table.addCell(body);
			}			
				
				PdfWriter.getInstance(doc, byteArrayOutputStream);
				doc.open();
				doc.add(table);
				doc.close();	
				System.out.println("preenchi pdf");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());		
	}

}
