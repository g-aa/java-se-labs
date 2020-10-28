package task12_readerwriterproblem;

import java.util.Random;

public class MyDataBase {

    private int m_readerCnt;
    private int m_writerCnt;
    private MySemaphore m_dbLock;
    private MySemaphore m_rLock;

    private Random m_random;
    private int m_maxTimeOut;

    public MyDataBase(int maxTimeOut) {
        m_readerCnt = 0;
        m_maxTimeOut = maxTimeOut;
        m_dbLock = new MySemaphore(1);
        m_rLock = new MySemaphore(1);
        m_random = new Random();
    }


    public void startRead(String rName) {
        try {
            m_rLock.acquire();
            // взять ресурс на чтение:
            if(m_readerCnt == 0) {
                m_dbLock.acquire();
            }
            m_readerCnt++;
            long timeOut = getTimeOut();
            Main.myPrinter(rName + " is reading (" + timeOut/1000.0 + " c.). Reader count: " + m_readerCnt + ", writer count: " + m_writerCnt);
            m_rLock.release();

            Thread.sleep(timeOut);
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }


    public void endRead(String rName) {
        try {
            m_rLock.acquire();
            m_readerCnt--;
            Main.myPrinter(rName + " is done reading. Reader count: " + m_readerCnt + ", writer count: " + m_writerCnt);
            m_rLock.release();

            // возврат ресурса на чтение:
            if (m_readerCnt == 0) {
                m_dbLock.release();
            }
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }


    public void startWrite(String vName) {
        try {
            // попробовать получить ресурс на запись:
            m_dbLock.acquire();

            m_writerCnt++;
            long timeOut = getTimeOut();
            Main.myPrinter(vName + " is writing (" + timeOut/1000.0 + " c.). Reader count: " + m_readerCnt + ", writer count: " + m_writerCnt);
            Thread.sleep(timeOut);
        }
        catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }


    public void endWrite(String vName) {
        m_writerCnt--;
        Main.myPrinter(vName + " is done writing. Reader count: " + m_readerCnt + ", writer count: " + m_writerCnt);

        // вернуть ресурс на запись:
        m_dbLock.release();
    }


    private long getTimeOut() {
        return m_random.nextInt(m_maxTimeOut);
    }
}