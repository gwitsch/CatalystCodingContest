package at.gwt.ccc.base.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

public final class FileUtils {

	private FileUtils() {
		// utility class
	}
	
	public static void writeSolution(Path outputPath, Consumer<BufferedWriter> contentWriter) throws IOException {
		try (BufferedWriter w = Files.newBufferedWriter(outputPath)) {
			contentWriter.accept(w);
		}
	}
}
