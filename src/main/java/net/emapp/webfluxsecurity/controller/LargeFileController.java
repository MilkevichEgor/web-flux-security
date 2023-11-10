package net.emapp.webfluxsecurity.controller;

import net.emapp.webfluxsecurity.download.LargeFileDownload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/large-file")
public class LargeFileController {

    @GetMapping
    public void get() throws IOException {
        LargeFileDownload.download();
    }

}
