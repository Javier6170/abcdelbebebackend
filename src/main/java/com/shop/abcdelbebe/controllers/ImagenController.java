package com.shop.abcdelbebe.controllers;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.model.entities.Imagen;
import com.shop.abcdelbebe.repository.ImagenRepository;
import com.shop.abcdelbebe.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("api/imagen")
@CrossOrigin
public class ImagenController {

    @Autowired
    ImagenRepository imagenRepository;

    @Autowired
    private ProductoService prodcutoService;

    @PostMapping("/upload")
    public ResponseEntity<?> crearImagen(@RequestParam("imageFile") MultipartFile file) throws IOException {
        Imagen img = new Imagen(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
        imagenRepository.save(img);
        return new ResponseEntity(new Mensaje("Se ha añadido con exito tu imagen"), HttpStatus.CREATED);
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    @GetMapping(path = { "/get/{imageName}" })
    public Imagen getImage(@PathVariable("imageName") String imageName) throws IOException {
        final Optional<Imagen> retrievedImage = imagenRepository.findByName(imageName);
        Imagen img = new Imagen(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }
}
