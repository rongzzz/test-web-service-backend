package com.rongzhe.demo.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.rongzhe.demo.mappers.UserMapper;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;

@WebServlet("/jasperreport")
public class JasperReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final private static String jasperReportName = JasperReportServlet.class.getClassLoader()
			.getResource("jasperreport/test-20210318.jasper").getPath();

	@Autowired
	private UserMapper userMapper;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("JasperReportServlet do Get");
		final String reportType = request.getParameter("reportType");
		final Date date = new Date();
		final SimpleDateFormat spdFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		final String fileName = String.format("UserReport-%s", spdFormat.format(date));

		final JRDataSource dataSource = new JRBeanCollectionDataSource(userMapper.getUserReportData());
		byte[] bytes = null;
		final ServletOutputStream ouputStream = response.getOutputStream();
		try (final ByteArrayOutputStream oStream = new ByteArrayOutputStream()) {
			final Map<String, Object> parameters = new HashMap<>();
			parameters.put("title", "User List");
			parameters.put("createTime", date);
			@SuppressWarnings("rawtypes")
			final JRAbstractExporter exporter;
			if ("Excel".equals(reportType)) {
				exporter = new JRXlsExporter();
			} else {
				exporter = new JRPdfExporter();
			}
			final JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReportName, parameters, dataSource);
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			JasperExportManager.exportReportToPdfStream(jasperPrint, oStream);

			bytes = oStream.toByteArray();

			if ("Excel".equals(reportType)) {
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");
				response.setContentType("application/vnd.ms-excel");
				response.setContentLength(bytes.length);
			} else {
				response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".pdf");
				response.setContentType("application/pdf");
				response.setContentLength(bytes.length);
			}

			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
		} catch (final JRException e) {
			response.reset();
			e.printStackTrace();
			final PrintWriter out = response.getWriter();
			out.println("Fail");
			out.flush();
			out.close();
		}
	}

}
