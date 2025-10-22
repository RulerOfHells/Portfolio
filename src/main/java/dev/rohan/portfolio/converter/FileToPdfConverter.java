package dev.rohan.portfolio.converter;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

import dev.rohan.portfolio.domain.FileFormat;

public class FileToPdfConverter extends AbstractHttpMessageConverter<FileFormat> {

	public FileToPdfConverter() {
		super(new MediaType("application", "pdf"));
	}
	
	@Override
	protected boolean supports(Class<?> clazz) {
		return FileFormat.class.isAssignableFrom(clazz);
	}

	@Override
	protected FileFormat readInternal(Class<? extends FileFormat> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException, UnsupportedOperationException {
		throw new UnsupportedOperationException("Reading not supported");
	}

	@Override
	protected void writeInternal(FileFormat t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		outputMessage.getHeaders().setContentType(MediaType.parseMediaType(t.getMimeType()));
		outputMessage.getHeaders().set("Content-Disposition", "inline; filename=\"" + t.getFile().getName() + "\"");
		FileCopyUtils.copy(new FileInputStream(t.getFile()), outputMessage.getBody());
	}

}
