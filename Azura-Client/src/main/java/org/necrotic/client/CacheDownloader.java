package org.necrotic.client;

import org.necrotic.Configuration;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
//you may start

/**
 * Handles cache downloading
 * blah balh **BAD ASS cODE ***
 */
public class CacheDownloader {

    private static final String CACHE_FILE_NAME = ".rs.zip"; //The name of the actual .zip file
    private static final String CACHE_URL = "https://www.dropbox.com/s/cnqtj5yb5p9sc7c/.rs.zip?dl=1";
    private static final String CACHE_URL1 = "https://www.dropbox.com/s/cnqtj5yb5p9sc7c/.rs.zip?dl=1";
    //private static final String CACHE_URL = "https://www.lunite.io/resources/Lunite.zip";
    //private static final String CACHE_URL1 = "https://download948.mediafire.com/pd15tyn48dog/kt9w5jzdaj9eeqy/Lunite.zip";
    private static final String NEWEST_VERSION_FILE_URL = "https://www.lunite.io/resources/cache_version.txt";
    private static final String NEWEST_VERSION_FILE_URL1 = "https://download1501.mediafire.com/xa8bmoogb7cg/ia96cpttjuqjoir/cache_version.txt";

    private static final String CURRENT_VERSION_FILE = "cache_version.txt"; //The location of the local cache_version txt file

    public static final String URL_TO_LOADING_IMAGES = "https://lunite.io/resources/";
    public static final String MIRROR_URL_TO_LOADING_IMAGES = "https://lunite.io/resources/"; //If first link is broken, it will attempt to download from here

    public static boolean UPDATING = true;

    public static boolean updatedCache() {
        try {
            double newest = getNewestVersion();
            double current = getCurrentVersion();
            if (cacheDownloadRequired(newest, current) || forceUpdateCache()) {
                if (Configuration.STOP_CACHE_UPDATES) {
                    System.out.println("Stopped a cache update from occuring due to current configuration.");
                } else {
                    if (forceUpdateCache() == true) {
                        System.out.println("We are localhost, and being forced to update cache.");
                    } else {
                        System.out.println("Updated rs Cache. No manual overrides detected, proceeding as normal. Current: " + current + ", Newest: " + newest);
                    }
                    downloadCache();
                    unzipCache();
                    setLatestCacheVersion(newest);
                }
            }

            UPDATING = false;
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        UPDATING = false;
        return false;

    }


    @SuppressWarnings("unused")
    public static boolean forceUpdateCache() {
        if (Configuration.SERVER_HOST().equalsIgnoreCase("localhost") && Configuration.FORCE_CACHE_UPDATE) {
            return true;
        }
        return false;
    }

    public static boolean cacheDownloadRequired(double newest, double current) {
        return newest != current;
    }

    public static void downloadCache() throws IOException {
        URL url = new URL(CACHE_URL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.addRequestProperty("User-Agent", "Mozilla/4.76");
        int responseCode = httpConn.getResponseCode();


        if (responseCode != HttpURLConnection.HTTP_OK) {
            downloadCache2();
            return;
        }
        System.out.println("First cache download");


        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = CACHE_FILE_NAME;
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = Signlink.getCacheDirectory() + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            long startTime = System.currentTimeMillis();
            int downloaded = 0;
            long numWritten = 0;

            if (httpConn.getContentLength() <=  0){
                downloadCache2();
                return;
            }
            int length = httpConn.getContentLength() > 0 ? httpConn.getContentLength() : 135445935;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                numWritten += bytesRead;
                downloaded += bytesRead;
                int percentage = (int) (((double) numWritten / (double) length) * 100D);
                Client.getClient().setLoadingPercentage(percentage);
            }

            outputStream.close();
            inputStream.close();

        } else {
            System.out.println("Cache host replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }


    public static void downloadCache2() throws IOException {
        System.out.println("Second cache download");
        URL url = new URL(CACHE_URL1);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.addRequestProperty("User-Agent", "Mozilla/4.76");
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            String fileName = CACHE_FILE_NAME;
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = Signlink.getCacheDirectory() + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            long startTime = System.currentTimeMillis();
            int downloaded = 0;
            long numWritten = 0;
            int length = httpConn.getContentLength() > 0 ? httpConn.getContentLength() : 135445935;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
                numWritten += bytesRead;
                downloaded += bytesRead;
                int percentage = (int) (((double) numWritten / (double) length) * 100D);
                Client.getClient().setLoadingPercentage(percentage);
            }

            outputStream.close();
            inputStream.close();

        } else {
            System.out.println("Cache host replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
    }


    private static void unzipCache() {
        try {
            final File file = new File(Signlink.getCacheDirectory() + CACHE_FILE_NAME);
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            ZipInputStream zin = new ZipInputStream(in);
            ZipEntry e;
            //new FinishedCacheDownload();
            while ((e = zin.getNextEntry()) != null) {
                if (e.isDirectory()) {
                    (new File(Signlink.getCacheDirectory() + e.getName())).mkdir();

                } else {

                    if (e.getName().equals(file.getName())) {
                        unzipPartlyArchive(zin, file.getName());
                        break;
                    }
                    unzipPartlyArchive(zin, Signlink.getCacheDirectory() + e.getName());
                }

            }

            zin.close();
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Unzips a partly archive
     *
     * @param zin The zip inputstream
     * @param s   The location of the zip file
     * @throws IOException The method can throw an IOException.
     */
    private static void unzipPartlyArchive(ZipInputStream zin, String s) throws Exception {
        FileOutputStream out = new FileOutputStream(s);
        //drawLoadingText(100, "Unpacking data..");
        byte[] b = new byte[1024];
        int len = 0;

        while ((len = zin.read(b)) != -1) {
            out.write(b, 0, len);
        }
        out.close();
    }

    public static double getCurrentVersion() {
        try {
            File file = new File(Signlink.getCacheDirectory() + CURRENT_VERSION_FILE);
            if (!file.exists()) {
                return 0.0;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            double version = Double.parseDouble(br.readLine());
            br.close();
            return version;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.1D;
    }

    public static double getNewestVersion() {
        try {
            System.out.println("First cache version link");
            URL url = new URL(NEWEST_VERSION_FILE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("User-Agent", "Mozilla/4.76");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            double version = Double.parseDouble(br.readLine());
            br.close();
            return version;
        } catch (Exception e) {
            System.out.println("Second cache version link");
            try {
                URL url = new URL(NEWEST_VERSION_FILE_URL1);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.addRequestProperty("User-Agent", "Mozilla/4.76");
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                double version = Double.parseDouble(br.readLine());
                br.close();
                return version;
            } catch (Exception e1) {
                System.out.println("HERE SECOND");
                e1.printStackTrace();
            }
        }
        return 0.1D;
    }

    public static void setLatestCacheVersion(double newest) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(Signlink.getCacheDirectory() + CURRENT_VERSION_FILE));
        bw.write("" + newest + "");
        bw.close();
    }
}