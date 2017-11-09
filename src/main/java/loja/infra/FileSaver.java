package loja.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {

	@Autowired
	private HttpServletRequest request;

	public String write(String folder, MultipartFile file) {

		try {

			String realPath = request.getServletContext().getRealPath("/" + folder);

			String path = realPath + "/" + file.getOriginalFilename();

			file.transferTo(new File(path));

			return folder + "/" + file.getOriginalFilename();

		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	}

}
