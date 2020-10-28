package task12_readerwriterproblem;

import java.util.Random;

public abstract class MyRW implements Runnable {

    private String m_name;
    private int m_id;
    protected MyDataBase m_dbLink;
    private int m_maxTimeOut;
    private Random m_random;

    public MyRW(String name, int id, MyDataBase dbLink, int maxTimeOut) {
        m_name = name;
        m_id = id;
        m_dbLink = dbLink;
        m_maxTimeOut = maxTimeOut;
        m_random = new Random();
    }

    public int getId() {
        return m_id;
    }

    public String getName() {
        return m_name;
    }

    public long getTimeOut() {
        return m_random.nextInt(m_maxTimeOut);
    }


    @Override
    public String toString() {
        return m_name + " №: " + m_id;
    }
}
