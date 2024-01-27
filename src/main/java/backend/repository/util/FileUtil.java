package backend.repository.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class FileUtil<T> {
    private final String fileName;

    public FileUtil(String fileName) {
        this.fileName = fileName;
    }

    public List<T> readFile(Function<String, T> mapper) throws IOException {
        var file = this.getClass().getClassLoader().getResourceAsStream(this.fileName);

        if (Objects.isNull(file)) {
            throw new IllegalArgumentException("Arquivo não encontrado");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file, StandardCharsets.UTF_8))) {
            return reader.lines()
                    .skip(1)
                    .map(mapper)
                    .toList();
        }
    }
}