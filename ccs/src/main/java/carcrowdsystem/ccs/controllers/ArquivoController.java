package carcrowdsystem.ccs.controllers;

import carcrowdsystem.ccs.entitys.ArquivoEntity;
import carcrowdsystem.ccs.repositorys.ArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.*;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("${uri.dev}/arquivos")
public class ArquivoController {

  @Autowired
  private ArquivoRepository arquivoRepository;

//  private Path diretorioBase = Path.of(System.getProperty("user.dir") + "/arquivos"); // projeto
  private Path diretorioBase = Path.of(System.getProperty("java.io.tmpdir") + "/arquivos"); // temporario

  @PostMapping("/upload")
  public ResponseEntity<ArquivoEntity> upload(@RequestParam("arquivo") MultipartFile file) {

    if (file.isEmpty()){
      return ResponseEntity.status(400).build();
    }

    if (!this.diretorioBase.toFile().exists()) {
      this.diretorioBase.toFile().mkdir();
    }

    String nomeArquivoFormatado = formatarNomeArquivo(file.getOriginalFilename());

    String filePath = this.diretorioBase + "/" + nomeArquivoFormatado;
    File dest = new File(filePath);
    try {
      file.transferTo(dest);
    } catch (IOException e) {
      e.printStackTrace();
      throw new ResponseStatusException(422, "Não foi possível salvar o arquivo", null);
    }

    ArquivoEntity arquivoEntity = new ArquivoEntity();
    arquivoEntity.setDataUpload(LocalDate.now());
    arquivoEntity.setNomeArquivoOriginal(file.getOriginalFilename());
    arquivoEntity.setNomeArquivoSalvo(nomeArquivoFormatado);
    ArquivoEntity arquivoEntityBanco = arquivoRepository.save(arquivoEntity);

    return ResponseEntity.status(200).body(arquivoEntityBanco);
  }

  @GetMapping("/download/{id}")
  public ResponseEntity<byte[]> download(@PathVariable Integer id){
    Optional  <ArquivoEntity> arquivoOptional = arquivoRepository.findById(id);

    if (arquivoOptional.isEmpty()) {
      return ResponseEntity.status(404).build();
    }

    ArquivoEntity arquivoEntityBanco = arquivoOptional.get();

    File file = this.diretorioBase.resolve(arquivoEntityBanco.getNomeArquivoSalvo()).toFile();
    try {
      InputStream fileInputStream = new FileInputStream(file);

      return ResponseEntity.status(200)
              .header("Content-Disposition",
                      "attachment; filename=" + arquivoEntityBanco.getNomeArquivoOriginal())
              .body(fileInputStream.readAllBytes());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      throw new ResponseStatusException(422, "Diretório não encontrado", null);
    } catch (IOException e) {
      e.printStackTrace();
      throw new ResponseStatusException(422, "Não foi possível converter para byte[]", null);
    }
  }

  private String formatarNomeArquivo(String nomeOriginal) {
    return String.format("%s_%s", UUID.randomUUID(), nomeOriginal);
  }
}
