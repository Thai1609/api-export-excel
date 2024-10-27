package com.deanshoes.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deanshoes.domain.Sheet1;
import com.deanshoes.domain.Sheet2;
import com.deanshoes.domain.Sheet3;
import com.deanshoes.domain.Sheet3Temp;

@Controller
public class FileDownloadController {

	private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
	Map<Integer, Sheet1> step1Map = null;
	Map<Integer, Sheet2> step2Map = null;
	Map<Integer, Sheet3> step3Map = null;
	Map<Integer, Sheet3Temp> step3TempMap = null;

	@GetMapping("/files/{filename:.+}")
	public ResponseEntity<Resource> read_EditAndDownloadExcelFile(@PathVariable String filename,
			RedirectAttributes redirectAttributes) throws IOException {
		// Resolve file path from the uploads directory
		Path filePath = Paths.get(UPLOAD_DIRECTORY).resolve(filename).normalize();

		// Check if the file exists
		File excelFile = filePath.toFile();
		if (!excelFile.exists()) {
			throw new RuntimeException("File not found: " + filename);
		}

		// Load the Excel file

		try (FileInputStream fileInputStream = new FileInputStream(excelFile);
				Workbook workbook = new XSSFWorkbook(fileInputStream)) {
			step1Map = new HashMap<>();
			step2Map = new HashMap<>();
			step3Map = new HashMap<>();
			step3TempMap = new HashMap<>();
			// Modify the first sheet

			Sheet sheet = workbook.getSheetAt(2);
			importDataSheet3(sheet);
			for (Sheet3 step3 : step3Map.values()) {
				System.out.println(step3);
			}

			Sheet sheet2 = workbook.getSheetAt(1);
			importDataSheet2(sheet2);
			for (Sheet2 step2 : step2Map.values()) {
				System.out.println(step2);
			}

			Sheet sheet1 = workbook.getSheetAt(0);
			importDataSheet1(sheet1);
			for (Sheet1 step1 : step1Map.values()) {
				System.out.println(step1);
			}

			int keyTempSheet3 = 0;
			for (Sheet3 step3 : step3Map.values()) {
				for (Sheet2 step2 : step2Map.values()) {
					if (step2.getPO().equals(step3.getPo())) {
						for (Sheet1 step1 : step1Map.values()) {
							if (step1.getConsolidated_Master_Order().equals(step2.getMRP())) {
								keyTempSheet3++;
								step3.setInternal_code(step1.getMaterial());
								step3.setStyle(step1.getOrder());

								putIfNotDuplicateValueSheet3Temp(keyTempSheet3,
										new Sheet3Temp(step3.getDate(), step3.getInternal_code(), step3.getOrde_type(),
												step3.getStyle(), step3.getModel_name(), step3.getDestination(),
												step3.getPrs(), step3.getEta(), step3.getUsage(), step3.getOrder_qty(),
												step3.getPo(), step3.getVendor_name(), step3.getMtl_code(),
												step3.getMaterial_name(), step3.getUnit(), step3.getPrice(),
												step3.getEtd()));

// 								
							}
						}
					}
				}
			}
			// Result
			sheet = workbook.getSheetAt(3);
			Row row3 = null;
			Cell cell3 = null;
			int rowNum3 = 8, cellNum3 = 0;
			for (Sheet3Temp step3temp : step3TempMap.values()) {
				row3 = sheet.createRow(rowNum3);
				//
				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getDate());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getInternal_code());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getOrde_type());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getStyle());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getModel_name());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getPrs());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getEta());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getUsage());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getOrder_qty());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getPo());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getVendor_name());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getMtl_code());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getMaterial_name());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getUnit());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getPrice());
				cellNum3++;

				cell3 = row3.createCell(cellNum3);
				cell3.setCellValue(step3temp.getEtd());

				rowNum3++;
				cellNum3 = 0;
			}
			// Write the updated workbook back to the file
			try (FileOutputStream fileOutputStream = new FileOutputStream(excelFile)) {
				workbook.write(fileOutputStream); // Save the changes
			}
		}

		// Load the updated file as a Resource for download
		Resource resource = new UrlResource(filePath.toUri());
		if (resource.exists() && resource.isReadable()) {

			// Prepare the response with file for download
			ResponseEntity<Resource> responseEntity = ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);

			return responseEntity;

		} else {
			throw new RuntimeException("Failed to read file: " + filename);
		}

	}

	public void importDataSheet3(Sheet sheet) {
		String date = "", internal_code = "", orde_type = "", style = "", model_name = "", destination = "", prs = "",
				eta = "", usage = "", order_qty = "", po = "", vendor_name = "", mtl_code = "", material_name = "",
				unit = "", price = "", etd = "";

		Row row = null;
		Cell cell = null;
		int rowNum = 8, cellNum = 10, countObj = 1;

		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		step3Map = new HashMap<>();

		// get data sheet 3
		while (!getCellValue(cell).equals("")) {

			cellNum = 0;

			cell = row.getCell(cellNum);
			date = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			internal_code = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			orde_type = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			style = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			model_name = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			destination = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			prs = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			eta = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			usage = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			order_qty = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			po = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			vendor_name = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			mtl_code = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			material_name = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			unit = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			price = getCellValue(cell).toString();
			cellNum++;

			cell = row.getCell(cellNum);
			etd = getCellValue(cell).toString();

			step3Map.put(countObj, new Sheet3(date, internal_code, orde_type, style, model_name, destination, prs, eta,
					usage, order_qty, po, vendor_name, mtl_code, material_name, unit, price, etd));

			cellNum = 10;
			rowNum++;
			countObj++;

			row = sheet.getRow(rowNum);
			cell = row.getCell(cellNum);
		}
	}

	public void importDataSheet2(Sheet sheet) {
		String MRP = "", PO = "";

		Row row = null;
		Cell cell = null;
		int rowNum = 1, cellNum = 6, countObj = 1;

		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);
		step2Map = new HashMap<>();

		// get data sheet 2
		while (!getCellValue(cell).equals("")) {

			cellNum = 0;

			cell = row.getCell(cellNum);
			MRP = getCellValue(cell).toString();

			cellNum = 6;
			cell = row.getCell(cellNum);
			PO = getCellValue(cell).toString();

//			step2Map.put(countObj, new Sheet2(MRP, PO));
			putIfNotDuplicateValueSheet2(countObj, new Sheet2(MRP, PO));

			cellNum = 6;
			rowNum++;
			countObj++;

			row = sheet.getRow(rowNum);

			if (row == null)
				break;

			cell = row.getCell(cellNum);
		}
	}

	public void importDataSheet1(Sheet sheet) {
		String order = "", material = "", consolidated_Master_Order = "";

		Row row = null;
		Cell cell = null;
		int rowNum = 1, cellNum = 2, countObj = 1;

		row = sheet.getRow(rowNum);
		cell = row.getCell(cellNum);

		// get data sheet 1
		while (!getCellValue(cell).equals("")) {

			cellNum = 0;
			cell = row.getCell(cellNum);
			order = getCellValue(cell).toString();

			cellNum = 2;
			cell = row.getCell(cellNum);
			consolidated_Master_Order = getCellValue(cell).toString();

			cellNum = 4;
			cell = row.getCell(cellNum);
			material = getCellValue(cell).toString();

			step1Map.put(countObj, new Sheet1(order, material, consolidated_Master_Order));
//			putIfNotDuplicateValue(countObj, new Sheet1(order, material, consolidated_Master_Order));
			rowNum++;
			countObj++;
			cellNum = 2;

			row = sheet.getRow(rowNum);
			if (row == null)
				break;
			cell = row.getCell(cellNum);
		}
	}

	public void putIfNotDuplicateValueSheet3Temp(Integer key, Sheet3Temp value) {
		if (!step3TempMap.containsValue(value)) {
			step3TempMap.put(key, value);
		}
	}

	public void putIfNotDuplicateValueSheet2(Integer key, Sheet2 value) {
		if (!step2Map.containsValue(value)) {
			step2Map.put(key, value);
		}
	}

	public static Object getCellValue(Cell cell) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

		switch (cell.getCellType()) {
		case STRING:
			return cell.getStringCellValue();

		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				return dateFormat.format(cell.getDateCellValue());
			} else {
				return (int) cell.getNumericCellValue();
			}
		case BOOLEAN:
			return cell.getBooleanCellValue();

		case FORMULA:
			FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
			return evaluator.evaluate(cell).getNumberValue();

		case BLANK:
			return "";

		default:
			return "Unsupported Cell Type";
		}
	}
}
