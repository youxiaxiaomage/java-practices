package com.yxxmg.plugin.jar;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/5
 */
public interface RandomAccessData {
    InputStream getInputStream() throws IOException;

    RandomAccessData getSubsection(long offset, long length);

    byte[] read() throws IOException;

    byte[] read(long offset, long length) throws IOException;

    long getSize();
}
