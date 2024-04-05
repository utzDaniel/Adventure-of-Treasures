package backend.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public final class FileUtil<T> {
    private final String fileName;

    public FileUtil(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile(List<String> lines) throws IOException {
        Path file = Paths.get(this.fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Erro ao escrever no arquivo: " + e.getMessage());
        }
    }

    public Map<Integer, T> readFile(Function<String, T> mapper) throws IOException {
        Path file = Paths.get(this.fileName);
        var map = new HashMap<Integer, T>();
        try {
            var lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            for (int i = 0; i < lines.size(); i++) {
                map.put(i, mapper.apply(lines.get(i)));
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return map;
    }

    public List<T> readFile(Function<String, T> mapper, int skip) throws IOException {
        Path arquivo = Paths.get(this.fileName);
        try {
            return Files.readAllLines(arquivo, StandardCharsets.UTF_8).stream()
                    .skip(skip)
                    .map(mapper)
                    .toList();

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return new ArrayList<>();
    }
}