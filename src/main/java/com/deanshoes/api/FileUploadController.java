package com.deanshoes.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api-export-excel")
public class FileUploadController {
	private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

	@GetMapping("/upload")
	public String index() {
		return "upload";
	}

	// Handle file upload
	@PostMapping("/upload")
	public String uploadExcelFile(@RequestParam("file") MultipartFile file, Model model,
			RedirectAttributes redirectAttributes) {

		// Check if the file is empty
		if (file.isEmpty()) {
			model.addAttribute("message", "Please select a file to upload.");
			return "upload";
		}

		try {
			// Create upload directory if it doesn't exist
			File directory = new File(UPLOAD_DIRECTORY);
			if (!directory.exists()) {
				directory.mkdir(); // Create the directory
			}

			// Save the file to the specified location
			Path filePath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
			Files.write(filePath, file.getBytes());

			redirectAttributes.addFlashAttribute("fileName", file.getOriginalFilename());

			return "redirect:/api-export-excel/download";

		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("message", "An error occurred while uploading the file.");
			return "upload"; // Return to the same upload form with the success/failure message
		}

	}

	// Show the success page
	@GetMapping("/download")
	public String success(Model model) {
		return "download";  
	}
}
