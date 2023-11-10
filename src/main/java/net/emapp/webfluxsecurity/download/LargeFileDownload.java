package net.emapp.webfluxsecurity.download;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LargeFileDownload {

    public static void download() throws IOException {

        String destination = "./uploads/large-file.dat";
        Resource resource = new FileSystemResource(Paths.get("/home/fusion/works/large-file.dat"));

        Flux<DataBuffer> flux1 = DataBufferUtils.read(resource, new DefaultDataBufferFactory(), 1024);

        Path path = Paths.get(destination);
        DataBufferUtils.write(flux1, path)
                .flatMap(file -> Mono.just(ResponseEntity.ok(new FileSystemResource(path))))
                .subscribe(System.out::println);
    }
}