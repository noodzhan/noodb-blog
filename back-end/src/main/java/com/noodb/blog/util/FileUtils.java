package com.noodb.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * 文件工具类
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/10/19 10:29 下午
 */
public class FileUtils {

  private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

  private static FileTypeHandler fileTypeHandler =
      fileType -> {
        if (fileType.equals("image/png")) {
          return ".png";
        } else if (fileType.equals("image/jpg")) {
          return ".jpg";
        } else {
          return null;
        }
      };

  /**
   * 根据multipartFile类型生成file
   *
   * @param multipartFile 接受的文件
   * @param path 路径
   * @return
   * @throws IOException
   * @see MultipartFile
   */
  public static String generateFileByMultipartFile(MultipartFile multipartFile, String path) {
    if (Objects.isNull(multipartFile) || Objects.isNull(path)) {
      logger.warn("multipartFile is null or path is null");
      return null;
    }
    String absolutePathFileName;
    try {
      String fileName = multipartFile.getName();
      String fileSuffix = fileTypeHandler.genFileSuffix(multipartFile.getContentType());
      absolutePathFileName = path + fileName + fileSuffix;
      FileOutputStream fos = new FileOutputStream(absolutePathFileName);
      byte[] readPool = new byte[1024];
      int len = multipartFile.getInputStream().read(readPool);
      while (len != -1) {
        fos.write(readPool, 0, len);
      }
      fos.close();
    } catch (IOException ioException) {
      ioException.printStackTrace();
      return null;
    }
    return absolutePathFileName;
  }

  interface FileTypeHandler {
    /**
     * 生成文件后缀
     *
     * @param fileType
     * @return 返回null，表示不支持的类型
     */
    String genFileSuffix(String fileType);
  }
}
