package backend.service.util;

import backend.service.interfaces.IFlyweight;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;

public final class FileUtil<T> {
    private final String fileName;

    public FileUtil(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile(IFlyweight flyweight) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.fileName))) {
            writer.write(flyweight.extrinsic());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T readFile(Function<String, T> mapper) throws IOException {
        Path arquivo = Paths.get(this.fileName);

        try {
            List<String> linhas = Files.readAllLines(arquivo);
            return mapper.apply(linhas.get(0));

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return null;
    }
}