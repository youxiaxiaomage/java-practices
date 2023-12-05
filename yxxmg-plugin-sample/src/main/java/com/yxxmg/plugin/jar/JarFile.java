// package com.yxxmg.plugin.jar;
//
// import java.io.File;
// import java.io.IOException;
// import java.io.InputStream;
// import java.net.MalformedURLException;
// import java.net.URL;
// import java.security.Permission;
// import java.util.Iterator;
// import java.util.jar.JarEntry;
//
/// **
// * @author : yxxmg
// * @version : 1.0
// * @description :
// * @since : 2023/10/5
// */
// public class JarFile extends AbstractJarFile implements Iterable<JarEntry> {
// private static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
// private URL url;
// private final RandomAccessDataFile rootFile;
// private static final String PROTOCOL_HANDLER = "java.protocol.handler.pkgs";
// private static final String HANDLERS_PACKAGE = "com.yxxmg.plugin.loader";
// private static final AsciiBytes META_INF = new AsciiBytes("META-INF/");
//
// public JarFile(File file) throws IOException {
// super(file);
// }
//
// @Override
// URL getUrl() throws MalformedURLException {
// if (url == null) {
// String file =this.rootFile.getFile().toURI() + this
// }
// return null;
// }
//
// @Override
// InputStream getInputStream() throws IOException {
// return null;
// }
//
// @Override
// JarFileType getJarFileType() {
// return null;
// }
//
// @Override
// Permission getPermission() {
// return null;
// }
//
// @Override
// public Iterator<JarEntry> iterator() {
// return null;
// }
// }
