package com.wasu.springboot.integration.utils;

import com.wasu.springboot.integration.exceptions.BaseCoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.*;

public class ZipUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * 使用gzip压缩
     *
     * @param primStr
     * @return
     */
    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        byte[] bytes = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
            bytes = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            out = null;
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            gzip = null;
        }
        return new sun.misc.BASE64Encoder().encode(bytes);
    }

    /**
     * 使用gzip解压缩
     *
     * @param compressedStr
     * @return
     */
    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            ginzip = new GZIPInputStream(new ByteArrayInputStream(compressed));

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
                out.flush();
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            out = null;
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ginzip = null;
        }
        return decompressed;
    }

    /**
     * 使用zip压缩
     *
     * @param str
     * @return
     */
    public static final String zip(String str) {
        if (str == null) return null;

        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr = new sun.misc.BASE64Encoder().encodeBuffer(compressed);
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            out = null;
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            zout = null;
        }
        return compressedStr;
    }

    /**
     * 使用zip解压缩
     *
     * @param compressedStr
     * @return
     */
    public static final String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            out = new ByteArrayOutputStream();
            zin = new ZipInputStream(new ByteArrayInputStream(compressed));
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            out = null;
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            zin = null;
        }
        return decompressed;
    }

    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的ZIP文件，并存放到zipFilePath
     *
     * @param sourceFilePath
     * @param zipFilePath
     * @param fileName
     * @param type
     * @return
     */
    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName, String type) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
            LOGGER.info("待压缩的文件目录" + sourceFilePath + "不存在");
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                File parent = zipFile.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdir();
                }
                if (zipFile.exists()) {
                    zipFile.delete();
                }
                File[] sourceFiles = sourceFile.listFiles();
                if (null == sourceFile || sourceFile.length() < 1) {
                    LOGGER.error("待压缩的文件目录" + sourceFilePath + "里面不存在文件，无需压缩");
                } else {
                    zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
                    byte[] bufs = new byte[2014 * 10];
                    for (int i = 0; i < sourceFiles.length; i++) {
                        String inFile = sourceFiles[i].getName();
                        String prefix = inFile.substring(inFile.lastIndexOf(".") + 1);
                        if (type != null) {
                            if (!type.equals(prefix)) {
                                continue;
                            }
                        }
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);

                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024 * 10);
                        int read = 0;
                        while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                            zos.write(bufs, 0, read);
                            zos.flush();
                        }
                    }
                    flag = true;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new BaseCoreException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new BaseCoreException(e);
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                fis = null;
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                bis = null;
                if (zos != null) {
                    try {
                        zos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                zos = null;
            }
        }
        return flag;
    }

    public static byte[] fileToZipReturnByte(String sourceFilePath, String zipFilePath, String fileName) {
        byte[] content = null;
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
            LOGGER.info("待压缩的文件目录" + sourceFilePath + "不存在");
        } else {
            try {
                File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
                File parent = zipFile.getParentFile();
                if (parent != null && !parent.exists()) {
                    parent.mkdir();
                }
                if (zipFile.exists()) {
                    zipFile.delete();
                }

                zos = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
                byte[] bufs = new byte[2014 * 10];
                if (sourceFile.isDirectory()) {
                    File[] files = sourceFile.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            ZipEntry zipEntry = new ZipEntry(file.getName());
                            zos.putNextEntry(zipEntry);
                            fis = new FileInputStream(file);
                            bis = new BufferedInputStream(fis, 1024 * 10);
                            int read = 0;
                            while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                                zos.write(bufs, 0, read);
                                zos.flush();
                            }
                            bis.close();
                        }
                    }
                } else {
                    ZipEntry zipEntry = new ZipEntry(sourceFile.getName());
                    zos.putNextEntry(zipEntry);
                    fis = new FileInputStream(sourceFile);
                    bis = new BufferedInputStream(fis, 1024 * 10);
                    int read = 0;
                    while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                        zos.write(bufs, 0, read);
                        zos.flush();
                    }
                }
                zos.flush();
                zos.close();
                content = FileUtils.read(zipFile);
                flag = true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new BaseCoreException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new BaseCoreException(e);
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                fis = null;
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                bis = null;
                if (zos != null) {
                    try {
                        zos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                zos = null;
            }
        }
        return content;
    }

    public static void exportToClient(HttpServletResponse resp, String fileName, String filePath) throws Exception {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/x-dowload");
        resp.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1") + "_.zip");

        OutputStream out = resp.getOutputStream();
        InputStream in = null;

        try {
            in = new FileInputStream(filePath);
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
                out.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            in = null;
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            out = null;
        }
    }
}
