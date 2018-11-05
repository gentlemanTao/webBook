package FileDownloaderThread;

import util.Debug;

/**
 * @Author: Gentleman
 * @Date: 2018/11/5 10:33
 * @Description:
 **/
public class CaseRunner {
    public static void main(String[] args) throws Exception {
        if (0 == args.length) {
            args = new String[] { "https://download.jetbrains.8686c.com/idea/ideaIU-2018.2.5.exe", "2", "3" };
        }
        main0(args);
    }

    public static void main0(String[] args) throws Exception {
        final int argc = args.length;
        BigFileDownloader downloader = new BigFileDownloader(args[0]);

        // 下载线程数
        int workerThreadsCount = 10;
        long reportInterval =1;

        Debug.info("downloading %s%nConfig:worker threads:%s,reportInterval:%s s.",
                args[0], workerThreadsCount, reportInterval);

        downloader.download(workerThreadsCount, reportInterval * 1000);
    }
}
